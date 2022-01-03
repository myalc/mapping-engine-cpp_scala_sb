#ifndef RECEIVEADAPTER_H_
#define RECEIVEADAPTER_H_

#include <string>
#include <thread>
#include "common.h"
#include "KafkaProducer.h"
using namespace std;

class ReceiveAdapter {
public:
    ReceiveAdapter(KafkaProducer* producer);
    virtual ~ReceiveAdapter() {}
    virtual void terminate() {}

    void operator()() {
        threaded_loop();
    }

protected:
    virtual void receive() = 0;
    virtual void threaded_loop() = 0;

    KafkaProducer* m_producer;
};

#endif /* RECEIVEADAPTER_H_ */
