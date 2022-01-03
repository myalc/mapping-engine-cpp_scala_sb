#include <iostream>
#include "UdpReceiver.h"

UdpReceiver::UdpReceiver(KafkaProducer* producer): ReceiveAdapter(producer) {}

void UdpReceiver::threaded_loop() {
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(800));
        receive();
    }
}

void UdpReceiver::receive() {
    string payload =  "received data over udp";
    cout << payload << endl;
    m_producer->produce(payload);
}
