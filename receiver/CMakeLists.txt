cmake_minimum_required(VERSION 3.1)
project (receiver)

set (CMAKE_CXX_STANDARD 11)

find_package(Threads)
find_package(Boost COMPONENTS system thread REQUIRED)
find_package(OpenSSL REQUIRED)

include_directories(${OPENSSL_INCLUDE_DIR})
include_directories(${Boost_INCLUDE_DIR})
include_directories(inc)

link_libraries(
  nghttp2
  ${LIBEVENT_OPENSSL_LIBRARIES}
  ${OPENSSL_LIBRARIES}
)

add_executable(receiver
  src/main.cpp
  inc/common.h
  inc/KafkaProducer.h
  src/KafkaProducer.cpp
  inc/ReceiveAdapter.h
  src/ReceiveAdapter.cpp
  inc/HttpReceiver.h
  src/HttpReceiver.cpp
  inc/Http2Receiver.h
  src/Http2Receiver.cpp
  inc/TcpReceiver.h
  src/TcpReceiver.cpp
  inc/UdpReceiver.h
  src/UdpReceiver.cpp
)

target_link_libraries(receiver
  nghttp2
  nghttp2_asio
  ${CMAKE_THREAD_LIBS_INIT}
  ${OPENSSL_LIBRARIES}
  ${Boost_LIBRARIES}
)

set(CMAKE_INSTALL_PREFIX /opt)
install(TARGETS receiver DESTINATION ${CMAKE_INSTALL_PREFIX})

#message(WARNING "Boost_INCLUDE_DIR: ${Boost_INCLUDE_DIR}")
#message(WARNING "Boost_LIBRARIES: ${Boost_LIBRARIES}")
#message(WARNING "Boost_VERSION: ${Boost_VERSION}")
#message(WARNING "OpenSSL_VERSION: ${OPENSSL_VERSION}")
#message(WARNING "OpenSSL_INCLUDE_DIR: ${OPENSSL_INCLUDE_DIR}")
#message(WARNING "OpenSSL_LIBRARIES: ${OPENSSL_LIBRARIES}")

