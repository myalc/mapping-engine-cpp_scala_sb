#ifndef TCPRECEIVER_H_
#define TCPRECEIVER_H_

#include <string>
#include "common.h"
#include "ReceiveAdapter.h"
#include "TcpReceiver.h"
#include "KafkaProducer.h"

class TcpReceiver: public ReceiveAdapter {
public:
    TcpReceiver(KafkaProducer* producer);
private:
    void receive();
    void threaded_loop();
};

#endif /* TCPRECEIVER_H_ */