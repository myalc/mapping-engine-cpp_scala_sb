package com.myalc.mappingengine.subscriber

import com.myalc.mappingengine.impl.model.SensorData
import akka.actor.ActorRef
import akka.pattern.ask
import akka.stream.scaladsl.Flow
import akka.util.Timeout
import akka.{Done, NotUsed}
import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import akka.stream.scaladsl.FlowWithContext


trait FlowHelper {

  implicit val timeOut = Timeout(5000.milli)
  val actorRef: ActorRef
  val parallelism = 8

  val terminateFlow: Flow[Any, Done, NotUsed] = Flow[Any].map(_ => Done)

  val forwardKafkaMessageToWorker: Flow[Any, Done, NotUsed] = Flow[Any]
    .mapAsync(parallelism) { kafkaMessageWithMeta =>
      (actorRef ? kafkaMessageWithMeta)
        .map { msg =>
          //println(s"Got my response from actor : $msg" + Done)
          Done
        }
        .recover {
          case ex: Exception =>
            println("Exception found while waiting for processor response: " + ex)
            Done
        }
    }

  val actorFlowSensorVoltage: Flow[Any, Done, NotUsed] = Flow[Any]
    .map { msg =>
      //println(s"Got message from kafka (voltage): [${msg.toString}] ")
      SensorData("mapping.sensor.voltage", msg.toString())
    }
    .via(forwardKafkaMessageToWorker)
    .via(terminateFlow)

  val actorFlowSensorTemperature: Flow[Any, Done, NotUsed] = Flow[Any]
    .map { msg =>
      //println(s"Got message from kafka (temperature): [${msg.toString}] ")
      SensorData("mapping.sensor.temperature", msg.toString())
    }
    .via(forwardKafkaMessageToWorker)
    .via(terminateFlow)

  val actorFlowSensorHumidity: Flow[Any, Done, NotUsed] = Flow[Any]
    .map { msg =>
      //println(s"Got message from kafka (humidity): [${msg.toString}] ")
      SensorData("mapping.sensor.humidity", msg.toString())
    }
    .via(forwardKafkaMessageToWorker)
    .via(terminateFlow)

}
