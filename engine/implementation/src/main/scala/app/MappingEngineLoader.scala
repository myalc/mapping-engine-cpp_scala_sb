package com.myalc.mappingengine.app

import akka.actor.ActorRef
import com.myalc.mappingengine.service.MappingEngineService
import com.myalc.mappingengine.kafka.consumer.MappingEngineKafkaConsumer
import com.myalc.mappingengine.impl.{MappingEngineServiceImpl, ProcessorActor}
import com.myalc.mappingengine.impl.model.{SensorData}
import com.myalc.mappingengine.subscriber.KafkaSubscriber
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceLocator}
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.playjson.JsonSerializerRegistry
import com.lightbend.lagom.scaladsl.playjson.JsonSerializer
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents

import scala.concurrent.ExecutionContext

import play.api.libs.json.Json
import play.api.libs.json.Format
import java.time.LocalDateTime


import akka.cluster.sharding.typed.scaladsl._
import play.api.libs.json._

import scala.collection.immutable.Seq
import com.myalc.mappingengine.impl.Holder


trait MappingEngineApplicationComponents extends LagomServerComponents 
  with CassandraPersistenceComponents 
{
  implicit def executionContext: ExecutionContext
  override lazy val lagomServer: LagomServer = serverFor[MappingEngineService](wire[MappingEngineServiceImpl])
  override lazy val jsonSerializerRegistry: JsonSerializerRegistry = MappingEngineSerializerRegistry
}

abstract class MappingEngineApplication(context: LagomApplicationContext) extends LagomApplication(context)
    with MappingEngineApplicationComponents
    with AhcWSComponents
    //with CassandraPersistenceComponents
    with LagomKafkaComponents 
{
  lazy val nimbleMappingEngineKafkaConsumer: MappingEngineKafkaConsumer = serviceClient.implement[MappingEngineKafkaConsumer]
  lazy val processorActorRef: ActorRef = ProcessorActor(actorSystem)  
  wire[KafkaSubscriber]
}

class MappingEngineLoader extends LagomApplicationLoader {
  
  override def load(context: LagomApplicationContext): LagomApplication =
    new MappingEngineApplication(context) {
      override lazy val circuitBreakerMetricsProvider =new com.lightbend.lagom.internal.client.CircuitBreakerMetricsProviderImpl(actorSystem)
      override def serviceLocator: ServiceLocator = NoServiceLocator
  }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication = {
    new MappingEngineApplication(context) with LagomDevModeComponents
  }

  override def describeService: Option[Descriptor] = Some(readDescriptor[MappingEngineService])
}



object MappingEngineSerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = Seq(
    JsonSerializer[SensorData]
    // TODO: add other classes
  )
}
