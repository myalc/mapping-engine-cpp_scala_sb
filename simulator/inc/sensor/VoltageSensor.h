#ifndef VOLTAGESENSOR_H_
#define VOLTAGESENSOR_H_

#include "AbstractSensor.h"

class VoltageSensor: public AbstractSensor {

public:
    VoltageSensor(string _id, TransportStrategy *_transporter);
    virtual ~VoltageSensor() {}

private:
    void threaded_loop();
    void setValue();
    string getJsonData();
};

#endif /* VOLTAGESENSOR_H_ */