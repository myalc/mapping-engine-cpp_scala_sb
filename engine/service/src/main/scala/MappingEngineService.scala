package com.myalc.mappingengine.service

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}


trait MappingEngineService extends Service {

  def helloWorld: ServiceCall[NotUsed, String]

  override final def descriptor: Descriptor = {
    import Service._
    named("mapping-engine-service")
      .withCalls(
        restCall(Method.GET, "/mappingengine/hello/world", helloWorld),
      )
      .withAutoAcl(true)
  }

}
