#ifndef TEMPERATURESENSOR_H_
#define TEMPERATURESENSOR_H_

#include "AbstractSensor.h"

class TemperatureSensor: public AbstractSensor {

public:
    TemperatureSensor(string _id, TransportStrategy *_transporter);
    virtual ~TemperatureSensor() {}

private:
    void threaded_loop();
    void setValue();
    string getJsonData();
};

#endif /* TEMPERATURESENSOR_H_ */