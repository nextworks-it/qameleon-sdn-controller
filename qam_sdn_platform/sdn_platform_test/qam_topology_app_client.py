import odlHttpClient as ohc
import json 

class TopologyAppRestClient:
	def __init__(self, ip, port):
		self.ip = ip
		self.port = port

	def get_topology(self):
		response = ohc.make_http_request(self.ip, self.port, "/restconf/operations/qameleon-topology:get-qam-topology","POST", None)
		resp_str = response.read().decode("utf-8")
		topology_json = json.loads(resp_str)
		return topology_json


	def create_topology(self, filename_topology):
		f = open(filename_topology,) 
		data = json.load(f) 
		f.close() 
		response = ohc.make_http_request(self.ip,  self.port, "/restconf/operations/qameleon-topology:create-qam-topology","POST", data)
		return response




	def create_light_path(self, filename_req):
		f = open(filename_req,) 
		data = json.load(f) 
		f.close() 
		response = ohc.make_http_request(self.ip,  self.port, "/restconf/operations/tapi-path-computation:compute-p-2-p-path","POST", data)
		return response

	def del_light_path(self, light_path_uuid):
		data ={}
		inner = {}
		inner["tapi-path-computation:path-id-or-name"]=str(light_path_uuid)
		data["tapi-path-computation:input"]=inner
		response = ohc.make_http_request(self.ip,  self.port, "/restconf/operations/tapi-path-computation:delete-p-2-p-path","POST", data)
		return response

	def get_light_path(self, light_path_uuid):
		data ={}
		inner = {}
		inner["tapi-path-computation:path-id-or-name"]=str(light_path_uuid)
		data["tapi-path-computation:input"]=inner
		response = ohc.make_http_request(self.ip,  self.port, "/restconf/config/tapi-common:context/tapi-path-computation:path-computation-context/path/"+light_path_uuid,"GET", None)
		return response


	def get_all_light_paths(self):
		response = ohc.make_http_request(self.ip,  self.port, "/restconf/config/tapi-common:context/tapi-path-computation:path-computation-context","GET", None)
		return response




	def disable_node(self, node_id):
		json_request = {}
		json_request_inner = {}
		json_request_inner['qameleon-topology:node-id']=node_id
		json_request['qameleon-topology:input']=json_request_inner

		response = ohc.make_http_request(self.ip,  self.port, "/restconf/operations/qameleon-topology:disable_node","POST", json_request)


	def disable_port(self, node_id, port_id):
		json_request = {}
		json_request_inner = {}
		json_request_inner['qameleon-topology:node-id']=node_id
		json_request_inner['qameleon-topology:port-id']=port_id
		json_request['qameleon-topology:input']=json_request_inner

		response = ohc.make_http_request(self.ip,  self.port, "/restconf/operations/qameleon-topology:disable_port","POST", json_request)
