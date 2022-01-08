package com.myalc.mappingengine.config

import com.myalc.mappingengine.utils.Constants
import org.apache.commons.codec.binary.Base64
import org.fusesource.scalate.{Template, TemplateEngine}
import upickle.default.{macroRW, ReadWriter => RW}
import com.typesafe.scalalogging.Logger

object Config {
  implicit val rw: RW[Config] = macroRW
}

case class Config(
  val configName: String = "",    // required, unique key
  var srcSchema: String = "",     // optional, base64 (mutable)
  var dstSchema: String = "",     // optional, base64 (mutable)
  var mapping: String = "",       // optional, base64 (mutable)
  val transform: Boolean = true,
  val dstTopic: String = "",
  val result2kafka: Boolean = false,
  val validateScr: Boolean = false,
  val validateDst: Boolean = false
)
{
  // internals
  var hasFnc: Boolean = false
  var hasLoop: Boolean = false
  var hasIndex: Boolean = false

  // scalate template engine
  var templateEngine: TemplateEngine = null
  var template: Template = null
  var loopTemplates: Map[String, Template] = Map()
  var loadMappings: Map[String, String] = Map()
  var modifiedMapping: String = null
  val logger = Logger(classOf[Config])
  var loadedModifiedMapping: Boolean = false

  def prepare: Unit = {
    decodeBase64
    calcInternals
    prepareTemplates
 }

  def calcInternals: Unit = {
    if (mapping.contains("{{#func_"))
      hasFnc = true
    if (mapping.split("\\{\\{#").length - 1 > mapping.split("\\{\\{#func_").length - 1)
      hasLoop = true
    if (mapping.contains("/*"))
      hasIndex = true
    logger.info(String.format("internals: hasFnc:%b, hasLoop:%b, hasIndex:%b", hasFnc, hasLoop, hasIndex))
  }

  def prepareTemplates: Unit = {
    // compile scalate templates
    templateEngine = new TemplateEngine
    template = templateEngine.compileMoustache(mapping)

    if (hasLoop) {
      val re = """\{\{#(.*?)+\}\}""".r
      val keys: List[String] = re.findAllIn(mapping).toList

      // create loop templates and compile moustaches
      loopTemplates.empty
      loadMappings.empty
      for (k <- keys) {
        val mp = mapping.substring(mapping.indexOf(k) + k.length, mapping.indexOf(k.replace("#", "/")))
        logger.info(String.format(" -->loop.mapping key:%s, mapping:%s", k, mp))
        loopTemplates += (k -> templateEngine.compileMoustache(mp))
        loadMappings += (k -> mp)
      }

      // modify main mapping and compile moustache
      modifiedMapping = mapping
      for (k <- keys) {
        if (!k.startsWith("{{#func_")) {
          modifiedMapping = modifiedMapping.substring(0, modifiedMapping.indexOf(k)) + k.replace("#", "m1.yl") + modifiedMapping.substring(modifiedMapping.indexOf(k.replace("#", "/")) + k.length)
        }
      }
      logger.info("Modified Mapping: " + modifiedMapping)
      template = templateEngine.compileMoustache(modifiedMapping)
    }
  }

  def loadLoopTemplate(key: String, loopMapping: String): Unit = {
    val t = templateEngine.compileMoustache(loopMapping)
    loopTemplates += (key -> t)
  }

  def reloadModifiedMapping: Unit = {
    if (!loadedModifiedMapping) {
      template = templateEngine.compileMoustache(modifiedMapping)
      //loadedModifiedMapping = true
    }
  }

  def decodeBase64: Unit = {
    if (Base64.isBase64(mapping.getBytes()))
      mapping = new String(Base64.decodeBase64(mapping), Constants.encoding)
    if (Base64.isBase64(dstSchema.getBytes()))
      dstSchema = new String(Base64.decodeBase64(dstSchema), Constants.encoding)
    if (Base64.isBase64(srcSchema.getBytes()))
      srcSchema = new String(Base64.decodeBase64(srcSchema), Constants.encoding)
  }

  def encodeBase64: Unit = {
    if (!Base64.isBase64(mapping.getBytes()))
      mapping = Base64.encodeBase64String(mapping.getBytes)
    if (!Base64.isBase64(dstSchema.getBytes()))
      dstSchema = Base64.encodeBase64String(dstSchema.getBytes)
    if (!Base64.isBase64(srcSchema.getBytes()))
      srcSchema = Base64.encodeBase64String(srcSchema.getBytes)
  }
}
