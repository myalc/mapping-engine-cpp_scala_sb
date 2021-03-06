# Engine

This project has been generated by the lagom/lagom-scala.g8 template. 
For instructions on running and testing the project, see https://www.lagomframework.com/get-started-scala.html.

Mapping engine receives sensor data from kafka topics and perform json schema validation on recevied data using the configured source json schema(draft7). Transforms message using the mapping configuration, then validates response against response json schema and puts the result to another kafka topic. 

Implementation receives voltage, temperature, and humidity sensor data from kafka topics and validates received data. Performs an aggregation on received data to get a common sensor data structure as a result.

## Kafka Subscriber
KafkaSubscriber uses a flow helper to dispatch data to Lagom's actors with parallelism. 

## Actor System
When a message received by processor actor it performs validation and mapping tasks and puts aggregated data to another kafka topic. Actor system does the actual job.

## Configuration
Each configuration is hold by an unique configName. Configuration contains source and destination json shemas, mapping as base64 encoded, destination kafka topic, flags for schema validations, transformation, and so on. Configuration object internally holds some useful information whether mapping has custom functions, loop, and indexes in. It also holds a result template for mapping. org.fusesource.scalate's TemplateEngine is used to get a formmated json object. Example configuration files and a readme about configuration can be found in implementation/src/main/resources/configs folder. 

## Validation
io.circe package is used to perform json schema validation. https://index.scala-lang.org/circe/circe-json-schema/circe-json-schema/0.2.0?target=_2.13
JsonSchema draft7: https://json-schema.org/draft-07/json-schema-release-notes.html

## Mapping
Keys defined in the mapping are extracted and iterated, each key applied to input data to get corresponding key value. As a result of that an hash map has been built. Template engine's layout function is called to get the desired json output.
Mustache template language is used for mapping. https://index.scala-lang.org/scalate/scalate/sbt-scalate-plugin/1.7.1?target=_2.10


## Installation
Install jdk 1.8: https://adoptopenjdk.net/?variant=openjdk8&jvmVariant=openj9
```sh
javac -version
```

install sbt 1.3.13: https://www.scala-sbt.org/download.html
```sh
sbt -version
```

## Compile and run
```sh
cd engine
sbt clean compile
sbt runAll
```

## Send message with kafka-console-producer.sh
Below is the sample message that receiver C++ application sends to engine application.
```sh
~/kafka_2.13-3.0.0/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic engine.mapper.inbound.sensor.voltage
> {"receiverTag": "tag1", "sensorData": {"id": "uuid1", "type": "voltage", "value": "220", "sampleTsUtcMillis": "1641585271036"}}
``` 