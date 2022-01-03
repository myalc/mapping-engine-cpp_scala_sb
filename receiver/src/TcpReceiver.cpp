#include <iostream>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

#include "common.h"
#include "TcpReceiver.h"

TcpReceiver::TcpReceiver(KafkaProducer* producer): ReceiveAdapter(producer) {}

void TcpReceiver::threaded_loop() {
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(1300));
        receive();
    }
}

void TcpReceiver::receive() {
    string payload = "received data over tcp";
    cout << payload << endl;
    m_producer->produce(payload);
}
