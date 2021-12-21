#include "ReceiveAdapter.h"

ReceiveAdapter::ReceiveAdapter(KafkaProducer* producer) {
    m_producer = producer;
}
