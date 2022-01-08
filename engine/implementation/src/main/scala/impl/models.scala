package com.myalc.mappingengine.impl.model

import com.lightbend.lagom.scaladsl.playjson.JsonSerializer
import play.api.libs.json.{Format, Json}


final case class SensorData(configName: String, message: String)

object SensorData {
  implicit val format: Format[SensorData] = Json.format
}