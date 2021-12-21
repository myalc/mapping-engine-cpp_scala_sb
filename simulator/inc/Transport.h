#include "common.h"

// Consider using adapter pattern instead of strategy
// https://stackoverflow.com/questions/46023431/difference-between-strategy-pattern-and-adapter

#ifndef TRANSPORT_H_
#define TRANSPORT_H_
class TransportStrategy {
public:
    virtual ~TransportStrategy() {}
    virtual void transport(std::string payload) = 0;
};
#endif /* TRANSPORT_H_ */


#ifndef TRANSPORTTCP_H_
#define TRANSPORTTCP_H_
class TransportStrategyTcp: public TransportStrategy {
public:
    void transport(std::string payload);
};
#endif /* TRANSPORTTCP_H_ */


#ifndef TRANSPORTUDP_H_
#define TRANSPORTUDP_H_
class TransportStrategyUdp: public TransportStrategy {
public:
    void transport(std::string payload);
};
#endif /* TRANSPORTUDP_H_ */


#ifndef TRANSPORTHTTP_H_
#define TRANSPORTHTTP_H_
class TransportStrategyHttp: public TransportStrategy {
public: 
    void transport(std::string payload);
};
#endif /* TRANSPORTHTTP_H_ */


#ifndef TRANSPORTHTTP2_H_
#define TRANSPORTHTTP2_H_
class TransportStrategyHttp2: public TransportStrategy {
public: 
    void transport(std::string payload);
};
#endif /* TRANSPORTHTTP2_H_ */
