#include <iostream>
#include "Transport.h"

void TransportStrategyHttp::transport(std::string payload) {
    std::cout << "Transport over Http: " << payload << std::endl;
}

