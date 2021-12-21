#include <iostream>
#include "Transport.h"

// TODO:: https://nghttp2.org/documentation/libnghttp2_asio.html

void TransportStrategyHttp2::transport(std::string payload) {
    std::cout << "Transport over Http2: " << payload << std::endl;
}

