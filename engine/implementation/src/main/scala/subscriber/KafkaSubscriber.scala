package com.myalc.mappingengine.subscriber

import akka.actor.ActorRef
import akka.pattern.ask
import akka.stream.scaladsl.Flow
import akka.util.Timeout
import akka.{Done, NotUsed}
import com.myalc.mappingengine.kafka.consumer.MappingEngineKafkaConsumer
import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


class KafkaSubscriber(kafkaConsumer: MappingEngineKafkaConsumer, actorRef1: ActorRef) extends FlowHelper {

  override val actorRef: ActorRef = actorRef1
  
  kafkaConsumer.doMapKafkaSensorVoltage.subscribe.withGroupId(kafkaConsumer.consumerGroupSensorVoltage).atLeastOnce {
    actorFlowSensorVoltage
  }

  kafkaConsumer.doMapKafkaSensorTemperature.subscribe.withGroupId(kafkaConsumer.consumerGroupSensorTemperature).atLeastOnce {
    actorFlowSensorTemperature
  }

  kafkaConsumer.doMapKafkaSensorHumidity.subscribe.withGroupId(kafkaConsumer.consumerGroupSensorHumidity).atLeastOnce {
    actorFlowSensorHumidity
  }
}
