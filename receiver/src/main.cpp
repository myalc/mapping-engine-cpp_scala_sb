#include <iostream>
#include <thread>
#include <list>
#include <iterator>
#include <signal.h>

#include "common.h"
#include "KafkaProducer.h"
#include "HttpReceiver.h"
#include "Http2Receiver.h"
#include "TcpReceiver.h"
#include "UdpReceiver.h"
using namespace std;

volatile int exit_request = 0;
list<ReceiveAdapter*> receivers;

void signal_handler(int sig) {
	cout << sig << " signal handled" << endl;
   // terminate using volatile variable
   exit_request = 1;

   // terminate by calling function
   list<ReceiveAdapter*>::iterator it;
   for(it = receivers.begin(); it != receivers.end(); ++it) 
      (*it)->terminate();
}

int main(int argc, char *argv[]){

   signal(SIGTERM, signal_handler);
   signal(SIGINT, signal_handler);

   list<thread*> threads;
   KafkaProducer producer;
  
   Http2Receiver http2Rec(8080, &producer);
   /*HttpReceiver httpRec(&producer);
   TcpReceiver tcpRec(&producer);
   UdpReceiver udpRec(&producer);*/

   receivers.push_back(&http2Rec);
   /*receivers.push_back(&httpRec);
   receivers.push_back(&tcpRec);
   receivers.push_back(&udpRec);*/

   thread th1(http2Rec);
   threads.push_back(&th1);
   /*thread th2(httpRec);
   threads.push_back(&th2);
   thread th3(tcpRec);
   threads.push_back(&th3);
   thread th4(udpRec);
   threads.push_back(&th4);*/
   
   // join threads
   list<thread*>::iterator it;
   for(it = threads.begin(); it != threads.end(); ++it) 
      (*it)->join();
   cout << threads.size() << " threads termianted" << endl;

   cout << "Main termianted" << endl;
   return SUCCESS;
}