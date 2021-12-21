#include <iostream>
#include "Transport.h"

void TransportStrategyTcp::transport(std::string payload) {
    std::cout << "Transport over Tcp: " << payload << std::endl;
}

