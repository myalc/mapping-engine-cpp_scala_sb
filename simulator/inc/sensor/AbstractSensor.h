#ifndef ABSTRACTSENSOR_H_
#define ABSTRACTSENSOR_H_

#include <inttypes.h>
#include <string>
#include <thread> 
#include "common.h"
#include "Transport.h"
using namespace std;

class AbstractSensor {

public:
	AbstractSensor(string _type, string _id, TransportStrategy *_transporter);
    virtual ~AbstractSensor() {}

    void operator()() {
        threaded_loop();
    }

protected:
    virtual void threaded_loop() = 0;
    virtual void setValue() = 0;
    virtual string getJsonData();
    virtual int getRandomDelayMillis();
    
    string m_type;
    string m_id;
    int m_value;

    TransportStrategy *transporter;
};

#endif /* ABSTRACTSENSOR_H_ */
