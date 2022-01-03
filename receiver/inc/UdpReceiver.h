#ifndef UDPRECEIVER_H_
#define UDPRECEIVER_H_

#include <string>
#include "common.h"
#include "ReceiveAdapter.h"
#include "UdpReceiver.h"
#include "KafkaProducer.h"
using namespace std;

class UdpReceiver: public ReceiveAdapter {
public:
    UdpReceiver(KafkaProducer* producer);
private:
    void receive();
    void threaded_loop();
};

#endif /* UDPRECEIVER_H_ */