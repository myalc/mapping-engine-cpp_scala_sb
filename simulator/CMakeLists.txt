cmake_minimum_required(VERSION 3.1)
project (simulator)

set (CMAKE_CXX_STANDARD 11)
find_package (Threads)


include_directories(inc)
include_directories(inc/sensor)

add_executable(simulator
  src/main.cpp
  inc/common.h
  inc/Transport.h
  inc/sensor/AbstractSensor.h
  inc/sensor/TemperatureSensor.h
  inc/sensor/HumiditySensor.h
  inc/sensor/VoltageSensor.h
  src/sensor/AbstractSensor.cpp
  src/sensor/TemperatureSensor.cpp
  src/sensor/HumiditySensor.cpp
  src/sensor/VoltageSensor.cpp
  src/transport/TransportStrategyTcp.cpp
  src/transport/TransportStrategyUdp.cpp
  src/transport/TransportStrategyHttp.cpp
  src/transport/TransportStrategyHttp2.cpp
)


target_link_libraries (simulator ${CMAKE_THREAD_LIBS_INIT})

set(CMAKE_INSTALL_PREFIX /opt)
install(TARGETS simulator DESTINATION ${CMAKE_INSTALL_PREFIX})

