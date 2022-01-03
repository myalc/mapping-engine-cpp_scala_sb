#include <iostream>
#include "HttpReceiver.h"

HttpReceiver::HttpReceiver(KafkaProducer* producer): ReceiveAdapter(producer) {}

void HttpReceiver::threaded_loop() {
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(1200));
        receive();  
    }
}

void HttpReceiver::receive() {
    string payload = "received data over http";
    cout << payload << endl;
    m_producer->produce(payload);
}
