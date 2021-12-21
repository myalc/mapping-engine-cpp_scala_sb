#include "VoltageSensor.h"

VoltageSensor::VoltageSensor(string _id, TransportStrategy *_transporter): AbstractSensor("Voltage", _id, _transporter) {
}

void VoltageSensor::threaded_loop() {
    
    while (!exit_request) {
        std::this_thread::sleep_for(std::chrono::milliseconds(getRandomDelayMillis()));
        setValue();
        transporter->transport(getJsonData());
    }
}

void VoltageSensor::setValue() {
    m_value = rand() % 20 + 220; // between 220 and 240
}

string VoltageSensor::getJsonData() {
    // TODO: create own format
    return AbstractSensor::getJsonData();  
}
