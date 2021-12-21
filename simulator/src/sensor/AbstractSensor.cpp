#include "common.h"
#include "AbstractSensor.h"

AbstractSensor::AbstractSensor(string _type, string _id, TransportStrategy *_transporter) {
    m_type = _type;
    m_id = _id;
    transporter = _transporter;
    srand (time(NULL));
}

string AbstractSensor::getJsonData() {
    return "{ \"id\": \"" + m_id + "\", \"type\": \"" + m_type + "\", \"value\": \"" + to_string(m_value) + "\"}";  
}

int AbstractSensor::getRandomDelayMillis() {
    return rand() % 4500 + 500; // between 500 and 5000
}
