package com.myalc.mappingengine.kafka.consumer

import com.lightbend.lagom.scaladsl.api.Service.{named, topic}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.Descriptor.TopicCall
import akka.stream.scaladsl.Source
import scala.concurrent.Future

trait MappingEngineKafkaConsumer extends Service {

    val consumerGroupSensorVoltage = "engine.mapper.inbound.sensor.voltage"
    val consumerGroupSensorTemperature = "engine.mapper.inbound.sensor.temperature"
    val consumerGroupSensorHumidity = "engine.mapper.inbound.sensor.humidity"
    
    def doMapKafkaSensorVoltage: Topic[String]
    def doMapKafkaSensorTemperature: Topic[String]
    def doMapKafkaSensorHumidity: Topic[String]

    final override def descriptor: Descriptor = {
        named("mapping-engine-kafka-consumer")
        .withTopics(
            topic(consumerGroupSensorVoltage, this.doMapKafkaSensorVoltage),
            topic(consumerGroupSensorTemperature, this.doMapKafkaSensorTemperature),
            topic(consumerGroupSensorHumidity, this.doMapKafkaSensorHumidity)
        )
        .withAutoAcl(true)
    }
}

