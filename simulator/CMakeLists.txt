cmake_minimum_required(VERSION 3.1)
project (simulator)

set (CMAKE_CXX_STANDARD 11)
find_package(Threads)
find_package(Boost COMPONENTS system thread REQUIRED)
find_package(OpenSSL REQUIRED)


include_directories(inc)
include_directories(inc/sensor)
include_directories(${OPENSSL_INCLUDE_DIR})
include_directories(${Boost_INCLUDE_DIR})

link_libraries(
  nghttp2
  ${LIBEVENT_OPENSSL_LIBRARIES}
  ${OPENSSL_LIBRARIES}
)

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

target_link_libraries(simulator
  nghttp2
  nghttp2_asio
  ${CMAKE_THREAD_LIBS_INIT}
  ${OPENSSL_LIBRARIES}
  ${Boost_LIBRARIES}
)

set(CMAKE_INSTALL_PREFIX /opt)
install(TARGETS simulator DESTINATION ${CMAKE_INSTALL_PREFIX})

