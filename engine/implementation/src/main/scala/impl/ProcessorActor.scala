package com.myalc.mappingengine.impl

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.{Done}
import com.typesafe.scalalogging.Logger
import com.myalc.mappingengine.config.ConfigLoader
import com.myalc.mappingengine.impl.model.SensorData
import com.myalc.mappingengine.engine.{Mapper, Validator}
import org.joda.time.{DateTime, Period}

import scala.io.Source
//import Version7._ // since 0.9.5 necessary
//import com.eclipsesource.schema._
//import com.eclipsesource.schema.drafts.Version4
//import com.eclipsesource.schema.drafts.Version7 // since 0.9.5 necessary
import play.api.libs.json._
import scala.util.{Success, Failure}

object Holder {
  val logger = Logger(classOf[ProcessorActor])
  var configLoader = new ConfigLoader {}
  logger.info("Loaded " + configLoader.configs.size + " configs")
}

class ProcessorActor extends Actor {

  override def receive: Receive = {
    case msg => 
      val sensorData = msg.asInstanceOf[SensorData]
      println("Message received for processing....: " + msg)

      val conf = Holder.configLoader.configs.getOrElse(sensorData.configName, null)
      if (conf == null) {
        val err = String.format("Cannot find a config for given config name: %s", sensorData.configName)
        Holder.logger.error(err)
      } else {
        val dtStart = DateTime.now()
        Holder.logger.info("Started at: " + dtStart)
                
        // validate request agains source json schema
        if (conf.validateScr) {
          val validator = new Validator {}
          validator.validate(sensorData.message, conf.srcSchema) match {
            case Success => Holder.logger.info("Source validation succeeded")
            case Failure => Holder.logger.error("Source validation failed")
          }
        }

        var product = ""
        // performs transformation
        if (conf.transform) {
          val mapper = new Mapper {}
          mapper.config = conf
          product = mapper.map(sensorData.message, sensorData.configName).toString
          Holder.logger.info("MAPPED: " + product)
        }

        // validate translated data agains destination json schema
        if (conf.validateDst) {
          val validator = new Validator {}
          validator.validate(product, conf.dstSchema) match {
            case Success => Holder.logger.info("Destination validation succeeded")
            case Failure => Holder.logger.error("Destination validation failed")
          }
        }

        // send result to kafka
        if (conf.result2kafka) {
          // TODO: send to kafka
        }

        val dtEnd = DateTime.now()
        Holder.logger.info("Ended at: " + dtEnd + " Diff: " + new Period(dtStart, dtEnd).getMillis + " millis")
      }
      sender() ! Done
  }

}

object ProcessorActor {
  def apply(system: ActorSystem): ActorRef = {
    system.actorOf(Props[ProcessorActor])
  }
}