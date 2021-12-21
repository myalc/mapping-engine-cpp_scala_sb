#include <iostream>
#include "UdpReceiver.h"

UdpReceiver::UdpReceiver(KafkaProducer* producer): ReceiveAdapter(producer) {}

void UdpReceiver::threaded_loop() {
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(800));
        string payload = receive();
        cout << payload << endl;
        m_producer->produce("topic4", payload);
    }
}

string UdpReceiver::receive() {
    return "received data over udp";
}
