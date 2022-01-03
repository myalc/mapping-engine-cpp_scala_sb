#include "KafkaProducer.h"

#include <boost/uuid/uuid_io.hpp>
#include <boost/uuid/uuid_generators.hpp>

KafkaProducer::KafkaProducer() {
}

string KafkaProducer::produce(string payload) {
    //m_lock.lock();
    string uuid = send2kafka("topicx", payload);
    //m_lock.unlock();
    return uuid;
}

string KafkaProducer::send2kafka(string topic, string payload) {
    cout << "KafkaProducer.send2kafka:" << topic << "-" << payload << endl;
    string uuid = getRandomUuid();
    // TODO: kafka integration
    return uuid;
}

string KafkaProducer::getRandomUuid() {
    boost::uuids::uuid a_uuid = boost::uuids::random_generator()();
    return boost::uuids::to_string(a_uuid);
}
