import http.client
from base64 import b64encode
import json

def make_http_request(hostname, port, endopoint, request, json_payload):
	connection = http.client.HTTPConnection(hostname, port=port)
	headers = {"Content-Type": "application/yang-data+json", "Accept":"application/yang-data+json"}

	if(json_payload==None):
		connection.request(request,endopoint,headers=headers)
	else:
		connection.request(request,endopoint,json.dumps(json_payload), headers=headers)

	response = connection.getresponse()
	print(response.status)
	#print(response.read().decode())
	return response.read().decode()



def del_light_path(light_path_id):
	del_json = {}
	del_json["uuid"]=light_path_id
	outer_json = {}
	outer_json["input"] = del_json

	print("light path id "+light_path_id)
	delete_response = make_http_request("10.30.8.74",1234, "/operations/tapi-path-computation:delete-p-2-p-path/", "POST", outer_json)
	print(json.dumps(delete_response, sort_keys=True, indent=4))


f = open("pce_req.json",)
data = json.load(f)
f.close()
print("Input JSON")
print(json.dumps(data, sort_keys=True, indent=4))
decoded_response = make_http_request("10.30.8.74",1234, "/operations/tapi-path-computation:compute-p-2-p-path/", "POST", data)
print("\nResponse (path uuid)")
response = json.loads(decoded_response)
print(json.dumps(response, sort_keys=True, indent=4))
path_uuid = response["output"]["service"]["path"][0]["path-uuid"]
ero = make_http_request("10.30.8.74",1234, "/data/tapi-common:context/tapi-path-computation:path-computation-context/path="+path_uuid+"/", "GET", None)
print("\nERO")
ero_json = json.loads(ero)
print(json.dumps(ero_json, sort_keys=True, indent=4))

del_light_path(path_uuid)
