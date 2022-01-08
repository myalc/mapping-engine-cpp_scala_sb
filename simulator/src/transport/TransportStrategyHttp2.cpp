#include <iostream>
#include "Transport.h"
#include <nghttp2/asio_http2_client.h>

TransportStrategyHttp2::TransportStrategyHttp2(std::string dest, int port): TransportStrategy(dest, port) { 
    std::ostringstream ss;
    ss << "http://" << m_dest << ":" << m_port << "/http2/api/v1/receive";
    m_uri = ss.str();
}

// TODO: Send messages with a single session. Open session and use multiple times. When the session gets broken recover automatically. 

void TransportStrategyHttp2::transport(std::string payload) {
    std::cout << "Transport over Http2: " << payload << std::endl;
    
    boost::system::error_code ec;
    boost::asio::io_service io_service;
    // connect to destination
    nghttp2::asio_http2::client::session sess(io_service, m_dest, std::to_string(m_port));

    sess.on_connect([&sess, &payload, this](boost::asio::ip::tcp::resolver::iterator endpoint_it) {
        boost::system::error_code ec;

        auto printer = [](const nghttp2::asio_http2::client::response &res) {
            res.on_data([](const uint8_t *data, std::size_t len) {
                if (len > 0) {
                    std::cout << "Response: ";
                    std::cout.write(reinterpret_cast<const char *>(data), len);
                    std::cout << std::endl;
                }
            });
        };

        auto req = sess.submit(ec, "POST", m_uri, payload);
        req->on_response(printer);
        req->on_close([&sess](uint32_t error_code) {
            sess.shutdown();
        });
    });

    sess.on_error([](const boost::system::error_code &ec) {
        std::cerr << "error: " << ec.message() << std::endl;
    });

    io_service.run();
}

