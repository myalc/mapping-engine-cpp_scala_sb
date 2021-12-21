#include "KafkaProducer.h"

KafkaProducer::KafkaProducer() {
}

void KafkaProducer::produce(string topic, string payload) {
    m_lock.lock();
    send2kafka(topic, payload);
    m_lock.unlock();
}

void KafkaProducer::send2kafka(string topic, string payload) {
    cout << "KafkaProducer.send2kafka:" << topic << "-" << payload << endl;
}
