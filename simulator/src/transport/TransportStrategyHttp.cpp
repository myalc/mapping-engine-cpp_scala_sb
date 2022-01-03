#include <iostream>
#include "Transport.h"

TransportStrategyHttp::TransportStrategyHttp(std::string dest, int port): TransportStrategy(dest, port) { }

void TransportStrategyHttp::transport(std::string payload) {
    std::cout << "Transport over Http: " << payload << std::endl;
}

