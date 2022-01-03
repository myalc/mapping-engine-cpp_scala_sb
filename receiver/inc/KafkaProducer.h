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
    string produce(string payload);
private:
    string send2kafka(string topic, string payload);
    string getRandomUuid();
    std::mutex m_lock;
};

#endif /* KAFKAPRODUCER_H_ */