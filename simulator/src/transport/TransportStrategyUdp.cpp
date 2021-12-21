#include <iostream>
#include "Transport.h"

void TransportStrategyUdp::transport(std::string payload) {
    std::cout << "Transport over Udp: " << payload << std::endl;
}

