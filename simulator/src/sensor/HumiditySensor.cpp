#include "HumiditySensor.h"

HumiditySensor::HumiditySensor(string _id, TransportStrategy *_transporter): AbstractSensor("Humidity", _id, _transporter) {
}

void HumiditySensor::threaded_loop() {
    
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(getRandomDelayMillis()));
        setValue();
        transporter->transport(getJsonData());
    }
}

void HumiditySensor::setValue() {
    m_value = rand() % 20 + 30; // between 30% and 50%
}

string HumiditySensor::getJsonData() {
    // TODO: create own format
    return AbstractSensor::getJsonData();  
}
