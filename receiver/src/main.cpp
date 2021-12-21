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

void signal_handler(int sig) {
	cout << sig << " signal handled" << endl;
   exit_request = 1;
}

int main(int argc, char *argv[]){

   signal(SIGTERM, signal_handler);
   signal(SIGINT, signal_handler);

   list<ReceiveAdapter*> receivers;
   list<thread*> threads;
   KafkaProducer producer;
  
   HttpReceiver httpRec(&producer);
   Http2Receiver http2Rec(&producer);
   TcpReceiver tcpRec(&producer);
   UdpReceiver udpRec(&producer);

   receivers.push_back(&httpRec);
   receivers.push_back(&httpRec);
   receivers.push_back(&tcpRec);
   receivers.push_back(&udpRec);

   thread th1(httpRec);
   thread th2(http2Rec);
   thread th3(tcpRec);
   thread th4(udpRec);

   threads.push_back(&th1);
   threads.push_back(&th2);
   threads.push_back(&th3);
   threads.push_back(&th4);

   // join threads
   list<thread*>::iterator it;
   for(it = threads.begin(); it != threads.end(); ++it) 
      (*it)->join();
   cout << threads.size() << " threads termianted" << endl;

   cout << "Main termianted" << endl;
   return SUCCESS;
}