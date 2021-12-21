#ifndef HTTP2RECEIVER_H_
#define HTTP2RECEIVER_H_

#include <string>
#include "common.h"
#include "ReceiveAdapter.h"
#include "Http2Receiver.h"
#include "KafkaProducer.h"
using namespace std;

class Http2Receiver: public ReceiveAdapter {
public:
    Http2Receiver(KafkaProducer* producer);
private:
    string receive();
    void threaded_loop();
};

#endif /* HTTP2RECEIVER_H_ */