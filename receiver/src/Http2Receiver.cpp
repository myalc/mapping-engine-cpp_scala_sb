#include <iostream>
#include "Http2Receiver.h"

Http2Receiver::Http2Receiver(KafkaProducer* producer): ReceiveAdapter(producer) {}

void Http2Receiver::threaded_loop() {
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(900));
        string payload = receive();
        cout << payload << endl;
        m_producer->produce("topic1", payload);
    }
}

string Http2Receiver::receive() {
    return "received data over http2";
}
