#ifndef HUMIDITYSENSOR_H_
#define HUMIDITYSENSOR_H_

#include "AbstractSensor.h"

class HumiditySensor: public AbstractSensor {

public:
    HumiditySensor(string _id, TransportStrategy *_transporter);
    virtual ~HumiditySensor() {}

private:
    void threaded_loop();
    void setValue();
    string getJsonData();
};

#endif /* HUMIDITYSENSOR_H_ */