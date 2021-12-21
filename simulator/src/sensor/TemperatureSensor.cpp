#include "TemperatureSensor.h"

TemperatureSensor::TemperatureSensor(string _id, TransportStrategy *_transporter): AbstractSensor("Temperature", _id, _transporter) {
}

void TemperatureSensor::threaded_loop() {
    
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(getRandomDelayMillis()));
        setValue();
        transporter->transport(getJsonData());
    }
}

void TemperatureSensor::setValue() {
    m_value = rand() % 20 + 10; // between 10 and 30
}

string TemperatureSensor::getJsonData() {
    // TODO: create own format
    return AbstractSensor::getJsonData();  
}
