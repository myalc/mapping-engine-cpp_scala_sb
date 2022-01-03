#include <iostream>
#include "Http2Receiver.h"

Http2Receiver::Http2Receiver(int port, KafkaProducer* producer): ReceiveAdapter(producer) {
    m_port = port;
    m_server = new nghttp2::asio_http2::server::http2();
}

void Http2Receiver::terminate() {
    cout << "Terminate Http2Receiver" << endl;
    m_server->stop();
}

void Http2Receiver::threaded_loop() {
    receive();
}

void Http2Receiver::receive() {
    boost::system::error_code ec;

    m_server->handle("/http2/api/v1/receive", [this](const nghttp2::asio_http2::server::request &req, const nghttp2::asio_http2::server::response &res) {
        thread::id this_id = this_thread::get_id();
        cout << "Request received at thread " << this_id << endl;
        req.on_data([this, &res](const uint8_t *data, size_t len) {
            if (len > 0) {
                char buff[len + 1];
                snprintf(buff, len + 1, "%s", reinterpret_cast<const char *>(data));
                //cout << "Received data: " << buff << endl;
                string uuid = this->m_producer->produce(buff);

                std::ostringstream respss;
                respss << "{ \"tag:\": \"" << uuid << "\"}";
                res.write_head(200);
                res.end(respss.str());
            }
        });
    });


    m_server->num_threads(NUM_RECEIVE_THREADS);
    if (m_server->listen_and_serve(ec, "0.0.0.0", to_string(m_port), true)) {
        cerr << "error: " << ec.message() << endl;
    }
    cout << "Server listening at 0.0.0.0:" << m_port << endl;
    m_server->join();
}
