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

Install nghttp2
```bash
wget https://github.com/nghttp2/nghttp2/releases/download/v1.46.0/nghttp2-1.46.0.tar.bz2
tar xf nghttp2-1.46.0.tar.bz2
cd nghttp2-1.46.0/
./configure --enable-asio-lib
sudo make install
ls -la /usr/local/lib/
ls -la /usr/local/include/nghttp2
```