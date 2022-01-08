package com.myalc.mappingengine.config

import com.typesafe.scalalogging.Logger
import upickle.default.read
import scala.io.Source
import java.io.File

trait ConfigLoader {

  val logger = Logger(classOf[Config])
  // hold configurations
  var configs: Map[String, Config] = refreshFromResources

  def refreshFromResources: Map[String, Config] = {
    val tmp = new java.io.File(".").getCanonicalPath
    val resourcesPath = new java.io.File("./implementation/src/main/resources/configs").getCanonicalPath
    processPath(resourcesPath)
  }

  def processPath(resourcesPath: String): Map[String, Config] = {
    val files = getListOfFiles(resourcesPath.toString)
    logger.info("refreshFromResources using " + resourcesPath + " directory.")
    
    var tmpConfigs: Map[String, Config] = Map()
    for (f <- files) {
      if (f.toString().toLowerCase().endsWith(".json")) {
        val jsOb = processFile(f.toString)
        tmpConfigs += (jsOb.configName -> jsOb)
      }
    }
    configs = tmpConfigs
    logger.info("refreshFromResources completed. " + configs.size + " item(s) loaded.")
    return configs
  }

  def processFile(file: String): Config = {
    logger.info("Load: " + file)
    val content = Source.fromFile(file).mkString
    logger.debug(" --> String: " + content)
    // deserialize json
    val jsOb = read[Config](content)
    logger.debug(" --> Json (before prepare): " + jsOb)
    jsOb.prepare
    logger.debug(" --> Json (after prepare): " + jsOb)
    logger.debug(String.format("   --> internals hasFnc:%b, hasLoop:%b, hasIndex:%b", jsOb.hasFnc, jsOb.hasLoop, jsOb.hasIndex))
    try {
      logger.debug("   --> configName: " + jsOb.configName)
      logger.debug("   --> srcSchema: " + jsOb.srcSchema)
      logger.debug("   --> dstSchema: " + jsOb.dstSchema)
      logger.debug("   --> mapping: " + jsOb.mapping)
    } catch {
      case e: Exception => logger.error("Configuration could not be validated. Config: " + file)
    }
    return jsOb
  }

  def getListOfFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()  // empty list
    }
  }

}
