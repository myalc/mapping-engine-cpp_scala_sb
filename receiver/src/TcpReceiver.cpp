#include <iostream>
#include "TcpReceiver.h"

TcpReceiver::TcpReceiver(KafkaProducer* producer): ReceiveAdapter(producer) {}

void TcpReceiver::threaded_loop() {
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(1300));
        string payload = receive();
        cout << payload << endl;
        m_producer->produce("topic3", payload);
    }
}

string TcpReceiver::receive() {
    return "received data over tcp";
}
