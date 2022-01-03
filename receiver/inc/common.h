#ifndef COMMON_H_
#define COMMON_H_

extern volatile int exit_request;

#define		FAILURE		1
#define		SUCCESS		0

#define CONN_RETRY_PERIOD 3
#define IPV4_CHAR_LEN sizeof("255.255.255.255")

#endif /* COMMON_H_ */