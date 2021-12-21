# mapping-engine-cpp_scala

```bash
cd mapping-engine-cpp_scala
docker images
docker-compose build
docker-compose up -d engine
docker-compose up -d adapter
docker-compose up -d ui
docker-compose up -d simulator

docker logs <id>
```

```bash
docker build -t myalc/mappingengine/engine .
docker run -d -p 8080:8080 myalc/mappingengine/engine

docker build -t myalc/mappingengine/adapter .
docker run -d -p 8080:8080 myalc/mappingengine/adapter

docker build -t myalc/mappingengine/ui .
docker run -d -p 8080:8080 myalc/mappingengine/ui

docker build -t myalc/mappingengine/simulator .
docker run -d -p 8080:8080 myalc/mappingengine/simulator

docker exec -it <id> bash
```
