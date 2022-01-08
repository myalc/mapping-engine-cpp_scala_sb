package com.myalc.mappingengine.impl

import akka.actor.ActorSystem
import akka.cluster.Cluster
import akka.cluster.routing.{ClusterRouterGroup, ClusterRouterGroupSettings}
import akka.cluster.sharding.typed.scaladsl.{ClusterSharding, EntityRef}
import akka.routing.ConsistentHashingGroup
import akka.util.Timeout
import akka.{Done, NotUsed}
import com.myalc.mappingengine.service
import com.myalc.mappingengine.service.MappingEngineService
import com.myalc.mappingengine.config.ConfigLoader
import com.myalc.mappingengine.engine.Mapper
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.transport.BadRequest
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}
import com.typesafe.scalalogging.Logger
import org.fusesource.scalate.TemplateEngine
import org.joda.time.{DateTime, Period}
import upickle.default.{macroRW, ReadWriter => RW, _}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._


class MappingEngineServiceImpl()(implicit ec: ExecutionContext) extends MappingEngineService {

  val logger = Logger(classOf[MappingEngineServiceImpl])
    
  override def helloWorld: ServiceCall[NotUsed, String] = ServiceCall { req =>
    scala.concurrent.Future.successful("Hello World!")
  }

}
