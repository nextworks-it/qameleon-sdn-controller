#include <stdio.h> 
#include <stdlib.h> 
#include <sys/types.h> 
#include <sys/socket.h> 
#include <netinet/in.h> 
#include <netdb.h> 
#include <errno.h> 
#include <signal.h> 
#include <unistd.h> 
#include <string.h> 
#include <arpa/inet.h> 
#include <sys/wait.h>

#define BUFFER_SIZE 10000
#define SERVER_NAME "127.0.0.1"
#define SERVER_PORT 12345

int send_string_over_socket(char * string);
void write_on_file(char * filename, char * msg);

