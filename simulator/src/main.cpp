#include <iostream>
#include <thread>
#include <future>
#include <list>
#include <iterator>
#include <signal.h>


#include "common.h"
#include "AbstractSensor.h"
#include "TemperatureSensor.h"
#include "HumiditySensor.h"
#include "VoltageSensor.h"
#include "Transport.h"
using namespace std;

volatile int exit_request = 0;

void signal_handler(int sig) {
	cout << sig << " signal handled" << endl;
   exit_request = 1;
}

int main(int argc, char *argv[]){

   signal(SIGTERM, signal_handler);
   signal(SIGINT, signal_handler);

   TransportStrategyHttp2 tHttp2("localhost", 8080);
   /*TransportStrategyTcp tTcp("receiver", 8081);
   TransportStrategyUdp tUdp("receiver", 8082);
   TransportStrategyHttp tHttp("receiver", 8083);*/
   
   list<AbstractSensor*> sensors;
   list<thread*> threads;

   // create sensors
   TemperatureSensor ts1("b26e33f0-87f9-42db-8d91-d83f97369de4", &tHttp2);
   thread th1(ts1);
   sensors.push_back(&ts1);
   threads.push_back(&th1);

   HumiditySensor hs1("cbd32d26-ac0d-47e4-9161-8efd4340a540", &tHttp2);
   thread th2(hs1);
   sensors.push_back(&hs1);
   threads.push_back(&th2);

   VoltageSensor vs1("49d1dc6d-6ce0-4274-b1d0-5f5fdf3fb1c5", &tHttp2);
   thread th3(vs1);
   sensors.push_back(&vs1);
   threads.push_back(&th3);

   /* sample uuids
   cbd32d26-ac0d-47e4-9161-8efd4340a540
   234ca97b-82bf-4b54-b283-1faadb043411
   fdc05df2-887f-460b-b207-ecb0766cbbcb
   8b42ed52-6c7b-4ef1-a319-3f789d4ffa40
   */

   // join threads
   list<thread*>::iterator it;
   for(it = threads.begin(); it != threads.end(); ++it) 
      (*it)->join();
   cout << threads.size() << " threads termianted" << endl;

   cout << "Main termianted" << endl;
   return SUCCESS;
}