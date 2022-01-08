package com.myalc.mappingengine.engine

import com.myalc.mappingengine.config.Config
import com.typesafe.scalalogging.Logger
import scala.util.{Success, Failure}

import io.circe.literal._
import io.circe.parser._
import io.circe.schema.Schema // The library only supports Draft 7 of the JSON Schema specification. (https://index.scala-lang.org/circe/circe-json-schema/circe-json-schema/0.2.0?target=_2.13)
import cats.data.Validated.Invalid
import cats.data.Validated.Valid
import io.circe.Json


trait Validator {

  val logger = Logger(classOf[Validator])

  def validate(jsonStr: String, jsonSchema: String): Any = {

    //logger.debug("JSON String:" + jsonStr)
    //logger.debug("JSON Schema:" + jsonSchema)

    val _jsonStr = parse(jsonStr).getOrElse(Json.Null)
    val _jsonSchema = parse(jsonSchema).getOrElse(Json.Null)

    val schema: Schema = Schema.load(_jsonSchema)
    schema.validate(_jsonStr) match {
        case Invalid(e) => { 
            logger.warn("Invalid:" + e)
            Failure
        }
        case Valid(a) => Success
    }
  }
}
