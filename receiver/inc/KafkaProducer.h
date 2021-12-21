#ifndef KAFKAPRODUCER_H_
#define KAFKAPRODUCER_H_

#include <string>
#include <thread>
#include <iostream>
#include <mutex>
#include "common.h"

using namespace std;

class KafkaProducer {
public:
    KafkaProducer();
    virtual ~KafkaProducer() {};
    void produce(string topic, string payload);
private:
    void send2kafka(string topic, string payload);
    std::mutex m_lock;
};

#endif /* KAFKAPRODUCER_H_ */