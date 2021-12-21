#include <iostream>
#include "HttpReceiver.h"

HttpReceiver::HttpReceiver(KafkaProducer* producer): ReceiveAdapter(producer) {}

void HttpReceiver::threaded_loop() {
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(1200));
        string payload = receive();
        cout << payload << endl;
        m_producer->produce("topic2", payload);
    }
}

string HttpReceiver::receive() {
    return "received data over http";
}
