import http.client
from base64 import b64encode
import json



def make_http_request(hostname, port, endopoint, request, json_payload):
	connection = http.client.HTTPConnection(hostname, port=port)
	userAndPass = b64encode(b"admin:admin").decode("ascii")
	headers = { 'Authorization' : 'Basic %s' %  userAndPass,  "Content-Type": "application/json"}
	if(json_payload==None):
		connection.request(request,endopoint,headers=headers)
	else:
		connection.request(request,endopoint,json.dumps(json_payload), headers=headers)	

	response = connection.getresponse()
	return response

