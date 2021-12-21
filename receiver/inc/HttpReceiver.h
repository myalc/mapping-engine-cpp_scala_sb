#ifndef HTTPRECEIVER_H_
#define HTTPRECEIVER_H_

#include <string>
#include "common.h"
#include "ReceiveAdapter.h"
#include "HttpReceiver.h"
#include "KafkaProducer.h"
using namespace std;

class HttpReceiver: public ReceiveAdapter {
public:
    HttpReceiver(KafkaProducer* producer);
private:
    string receive();
    void threaded_loop();
};

#endif /* HTTPRECEIVER_H_ */