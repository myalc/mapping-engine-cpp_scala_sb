package com.myalc.mappingengine.engine

import com.myalc.mappingengine.config.Config
import com.typesafe.scalalogging.Logger
import scala.collection.mutable.{ArrayBuffer, LinkedHashMap}

trait Mapper {

  val logger = Logger(classOf[Mapper])
  var config: Config = null
  val mapperFunctions = new MapperFunctions {}
  
  def map(req: Any, configName: String): Any = {
    logger.debug(String.format("map using configName: %s", configName))
    logger.debug(String.format("received data: %s", req))

    // find all keys
    val re = """\{\{(.*?)+\}\}""".r
    val mappingKeys: List[String] = re.findAllIn(config.mapping).toList
    logger.debug(String.format("mappingKeys[%d] are: %s", mappingKeys.length, mappingKeys))

    var attributes: Map[String, Any] = Map()
    var needUnescape = false

    for (k <- mappingKeys) {
      var mkey = k.replace("{{", "").replace("}}", "")//.replace("#", "")
      var vl: Any = null

      if (!k.startsWith("{{#func_")) {
        vl = jsPick(ujson.read(req.toString), mkey.replace("#", "").split("/").toList)
        //println(mkey + " --> " + vl)

        if (vl.isInstanceOf[LinkedHashMap[_, _]]) {
          vl = hashMap2Json(vl)
          needUnescape = true
        }
        else if (vl.isInstanceOf[ArrayBuffer[_]] && !k.startsWith("{{#")) {
          vl = array2Json(vl)
          needUnescape = true
        }
        else if (vl.isInstanceOf[ArrayBuffer[_]]) {
          // loops
          vl = handleLoop(vl, k)
          mkey = mkey.replace("#", "m1.yl")
          logger.debug(String.format(" loop.key: %s, value: %s", mkey, vl))
        }

        logger.debug(String.format(" key: %s, value: %s", mkey, vl))
        attributes += (mkey -> vl)
      }
      else {
        logger.debug(String.format("handle function for: %s, %s", k, mkey))
        mkey match {
          case "#func_epochMillis2isoDt" => attributes += (mkey.replace("#", "") -> ((text: String) => mapperFunctions.func_epochMillis2isoDt(text)))
          // TODO : implement custom functions
          /*case "#func_wrapped" => attributes += (mkey.replace("#", "") -> ((text: String) => mapperFunctions.myFnc1(text)))
          case "#func_wrapped2" => attributes += (mkey.replace("#", "") -> ((text: String) => mapperFunctions.myFnc2(text)))*/
          case _ =>
        }
      }
    } //for

    var product = config.templateEngine.layout("/do/map/" + configName, config.template, attributes)
    if (needUnescape)
      product = unescape(product)
    logger.debug("Mapping result: " + product)
    return product
  }

  def handleLoop(vl: Any, keys: String): Any = {
    val original = vl.asInstanceOf[ArrayBuffer[Any]]
    val loopMapping = config.loadMappings.getOrElse(keys, "")
    val loopTemplate = config.loopTemplates.getOrElse(keys, null)
    if ("".equals(loopMapping) || loopTemplate == null)
      throw new Exception("Loop configuration is not available for: " + keys)

    // find all keys
    val re = """\{\{(.*?)+\}\}""".r
    val mappingKeys: List[String] = re.findAllIn(loopMapping).toList
    logger.debug(String.format("  handleLoop.mappingKeys[%d] are: %s", mappingKeys.length, mappingKeys))

    var cnt = 0
    var x = new StringBuilder("[")
    for (item <- original) {
      logger.debug(String.format("  handleLoop.item: %s", item))

      // iterate through mapping keys
      var needUnescape = false
      var attributes: Map[String, Any] = Map()
      for (k <- mappingKeys) {
        var mkey = k.replace("{{", "").replace("}}", "").replace("#", "")
        var vl = jsPick(ujson.read(item.toString), mkey.split("/").toList)

        if (vl.isInstanceOf[LinkedHashMap[_, _]]) {
          vl = hashMap2Json(vl)
          needUnescape = true
        }
        else if (vl.isInstanceOf[ArrayBuffer[_]] && !k.startsWith("{{#")) {
          vl = array2Json(vl)
          needUnescape = true
        }
        else if (vl.isInstanceOf[ArrayBuffer[_]]) {
          // loops
          vl = handleLoop(vl, k)
          mkey = mkey.replace("#", "m1.yl")
          logger.debug(String.format("  loop.key: %s, value: %s", mkey, vl))
        }

        logger.debug(String.format("  handleLoop.key: %s, value: %s", mkey, vl))
        attributes += (mkey -> vl)
      }

      var p = config.templateEngine.layout("/do/map/" + config.configName + keys, loopTemplate, attributes)
      if (needUnescape)
        p = unescape(p)

      if (cnt > 0)
        x ++= String.format(", %s", p)
      else
        x ++= String.format("%s", p)

      logger.debug("handleLoop.Mapping result: " + p)
      cnt += 1
    }
    return x ++= "]"
  }

  def jsPick(jsonVal: ujson.Value, keys: List[String]): Any = {
    if (keys.length == 1) {
      if (keys(0).startsWith("*")) {
        //jsonVal(keys(0).replace("*", "").toInt) //.value
        jsonVal(keys(0).replace("*", "").toInt).value
      } else {
        try {
          //jsonVal(keys(0)) //.value
          jsonVal(keys(0)).value
        } catch {
          case e: NoSuchElementException => "null"
        }
      }
    } else {
      if (keys(0).startsWith("*")) {
        jsPick(jsonVal(keys(0).replace("*", "").toInt), keys.drop(1))
      } else {
        try {
          jsPick(jsonVal(keys(0)), keys.drop(1))
        } catch {
          case e: NoSuchElementException => null
        }
      }
    }
  }

  def hashMap2Json(vl: Any): Any = {
    val original = vl.asInstanceOf[LinkedHashMap[Any, Any]]
    //println("hashMap2Json: " + original)
    var x = new StringBuilder("{")
    var cnt = 0
    for ((k, v) <- original) {
      logger.debug(String.format("  key: %s, value: %s", k, v))
      if (cnt > 0)
        x ++= String.format(", \"%s\": %s", k, v)
      else
        x ++= String.format("\"%s\": %s", k, v)
      cnt += 1
    }
    x ++= "}"
  }

  def array2Json(vl: Any): Any = {
    val original = vl.asInstanceOf[ArrayBuffer[Any]]
    //println("array2Json: " + original)
    var x = new StringBuilder("[")
    var cnt = 0
    for (item <- original) {
      logger.debug(String.format("  item: %s", item))
      if (cnt > 0)
        x ++= String.format(", %s", item)
      else
        x ++= String.format("%s", item)
      cnt += 1
    }
    x ++= "]"
  }

  val unescapeMap = Map("amp" -> "&", "lt" -> "<", "apos" -> "'", "quot" -> "\"", "gt" -> ">")
  def unescape(str: String) = {
    val result = str.foldLeft[(String, Option[String])](("", None)) { case ((acc, escapedAcc), c) =>
      (c, escapedAcc) match {
        case ('&', None) =>
          (acc, Some(""))
        case (_, None) =>
          (acc + c, None)
        case ('&', Some(_)) =>
          throw new IllegalArgumentException("nested escape sequences")
        case (';', Some(escapedAcc1)) =>
          (acc + unescapeMap(escapedAcc1), None)
        case (_,  Some(escapedAcc1)) =>
          (acc, Some(escapedAcc1 + c))
      }
    }

    result match {
      case (escaped, None) =>
        escaped
      case (_, Some(_)) =>
        throw new IllegalArgumentException("unfinished escape sequence")
    }
  }

}
