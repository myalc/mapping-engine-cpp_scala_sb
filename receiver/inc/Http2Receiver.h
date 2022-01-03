#ifndef HTTP2RECEIVER_H_
#define HTTP2RECEIVER_H_

#include <string>
#include "common.h"
#include "ReceiveAdapter.h"
#include "Http2Receiver.h"
#include "KafkaProducer.h"
#include <nghttp2/asio_http2_server.h>

class Http2Receiver: public ReceiveAdapter {
public:
    Http2Receiver(int port, KafkaProducer* producer);
    void terminate();
private:
    void receive();
    void threaded_loop();

    int m_port;
    nghttp2::asio_http2::server::http2 *m_server;
};

#endif /* HTTP2RECEIVER_H_ */