# MappingEngine-UI

Spring Boot Thymeleaf web application. 

## Install Required Packages
```bash
sudo apt-get install maven
mvn -version 
```

## Run 
```sh
cd ui
mvn package
mvn clean spring-boot:run
mvn package; java -jar target/ui-1.0-SNAPSHOT.jar com.myalc.mappingengine.ui.Application
```

Browse <a href="localhost:8080/configs">localhost:8080/configs</a>
