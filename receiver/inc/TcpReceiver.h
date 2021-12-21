#ifndef TCPRECEIVER_H_
#define TCPRECEIVER_H_

#include <string>
#include "common.h"
#include "ReceiveAdapter.h"
#include "TcpReceiver.h"
#include "KafkaProducer.h"
using namespace std;

class TcpReceiver: public ReceiveAdapter {
public:
    TcpReceiver(KafkaProducer* producer);
private:
    string receive();
    void threaded_loop();
};

#endif /* TCPRECEIVER_H_ */