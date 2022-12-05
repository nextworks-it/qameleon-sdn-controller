#include "socket_writer.h"


int send_string_over_socket(char * string){
	printf("Going to send a message\n");
	int sock = 0, valread; 
	struct sockaddr_in serv_addr;  
	char buffer[BUFFER_SIZE] = {0}; 
	if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0) 
	{ 
	printf("\n Socket creation error \n"); 
	return -1; 
	} 
   
	serv_addr.sin_family = AF_INET; 
	serv_addr.sin_port = htons(SERVER_PORT); 
       
	// Convert IPv4 and IPv6 addresses from text to binary form 
	if(inet_pton(AF_INET, SERVER_NAME, &serv_addr.sin_addr)<=0){ 
		printf("\nInvalid address/ Address not supported \n"); 
	return -1; 
    } 
   
    if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) { 
	printf("\nConnection Failed \n"); 
	return -1; 
	} 
	send(sock , string , strlen(string) , 0 ); 
	printf("Message sent\n"); 
	close(sock);
	return 0; 
}

void write_on_file(char * filename, char * msg){
	FILE * fp;
	int i;
	fp = fopen (filename,"a");
	fprintf (fp, "%s\n",msg);

	fclose (fp);
}

