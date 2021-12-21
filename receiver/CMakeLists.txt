cmake_minimum_required(VERSION 3.1)
project (receiver)

set (CMAKE_CXX_STANDARD 11)
find_package (Threads)


include_directories(inc)

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


target_link_libraries (receiver ${CMAKE_THREAD_LIBS_INIT})

set(CMAKE_INSTALL_PREFIX /opt)
install(TARGETS receiver DESTINATION ${CMAKE_INSTALL_PREFIX})

