#include <iostream>
#include "Transport.h"

TransportStrategyTcp::TransportStrategyTcp(std::string dest, int port): TransportStrategy(dest, port) { }

void TransportStrategyTcp::transport(std::string payload) {
    std::cout << "Transport over Tcp: " << payload << std::endl;
}

