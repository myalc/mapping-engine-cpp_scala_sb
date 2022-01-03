#include <iostream>
#include "Transport.h"

TransportStrategyUdp::TransportStrategyUdp(std::string dest, int port): TransportStrategy(dest, port) { }

void TransportStrategyUdp::transport(std::string payload) {
    std::cout << "Transport over Udp: " << payload << std::endl;
}

