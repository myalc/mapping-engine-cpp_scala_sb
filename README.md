# mapping-engine-cpp_scala_sb

Simulator (C++) generates sensor (temperature, humudity, voltage) data randomly. And sends to receiver over Http, Http2, Tcp, Udp. 

Receiver (C++) receives sensor data over Http, Http2, Tcp, Udp and puts into Kafka. 

Engine (scala-lagom) will gets messages from kafka, performs json mappings and puts to kafka. 

Ui (spring-boot) will demostrate received and transformed sensor data via web-sockets.


```bash
cd mapping-engine-cpp_scala
docker images
docker-compose build
docker-compose up -d engine
docker-compose up -d receiver
docker-compose up -d ui
docker-compose up -d simulator

docker logs <id>
```

```bash
docker build -t myalc/mappingengine/engine .
docker run -d -p 8080:8080 myalc/mappingengine/engine

docker build -t myalc/mappingengine/receiver .
docker run -d -p 8080:8080 myalc/mappingengine/receiver

docker build -t myalc/mappingengine/ui .
docker run -d -p 8080:8080 myalc/mappingengine/ui

docker build -t myalc/mappingengine/simulator .
docker run -d -p 8080:8080 myalc/mappingengine/simulator

docker exec -it <id> bash
```
