import odlHttpClient as ohc
import sys, json, time
from qam_topology_app_client import TopologyAppRestClient
topology_filename = 'topology_dummy_nodes.json'

def test_topology_creation(tarc):
	response = tarc.create_topology(topology_filename)
	if(response.status==200):
		print("Topology correctly created")
		print("TEST PASSED")
	else:
		print("TEST FAILED")
	f = open(topology_filename,) 
	data = json.load(f) 
	return data
 
def test_get_topology(topology_json, tarc):
	response = tarc.get_topology();	
	actual_link_count = len(response['output']['qam-topology-out']['qam-link']);
	actual_node_count = len(response['output']['qam-topology-out']['qam-node']);
	expected_link_count = len(topology_json['qameleon-topology:input']['qameleon-topology:topology']['qameleon-topology:qam-link'])
	expected_node_count = len(topology_json['qameleon-topology:input']['qameleon-topology:topology']['qameleon-topology:qam-node'])
	
	if(actual_link_count==expected_link_count):
		print("Correct link count: TEST PASSED")
	else:
		print("Wrong link count: TEST FAILED")


	if(actual_node_count==expected_node_count):
		print("Correct node count: TEST PASSED")
	else:
		print("Wrong node count: TEST FAILED")

	expected_uuid= topology_json['qameleon-topology:input']['qameleon-topology:topology']['qameleon-topology:topology-uuid']
	actual_uuid = response['output']['qam-topology-out']['topology-uuid']
	if(actual_node_count==expected_node_count):
		print("Correct topology Uuid: TEST PASSED")
	else:
		print("Wrong topology Uuid: TEST FAILED")


def create_light_path_test(tarc, req_file_name):
	response = tarc.create_light_path(req_file_name)
	parsed = json.loads(response.read().decode("utf-8"))
	print(parsed)
	if(parsed['output']!=None):
		light_path_uuid = parsed['output']['service']['uuid']
		print("Light path correctly created: TEST PASSED")
		return light_path_uuid
	else:
		print("Light path cannot be correctly created: TEST FAILED")

def get_light_path_test(tarc, light_path_uuid):
		response = tarc.get_light_path(light_path_uuid)
		parsed = json.loads(response.read().decode("utf-8"))
		link_count = len(parsed['tapi-path-computation:path'][0]['link'])
		if(link_count>0 and parsed['tapi-path-computation:path'][0]['uuid']==light_path_uuid):
			print("Light path correctly get: TEST PASSED")
		else:
			print("Light path NOT correctly get: TEST FAILED")

def get_all_light_paths_test(tarc, light_path_uuid):
		response = tarc.get_all_light_paths()
		parsed = json.loads(response.read().decode("utf-8"))
		print(parsed)
		ligth_path_count = parsed['tapi-path-computation:path-computation-context']['path']
		if(len(ligth_path_count)>0):
			print("Light paths correctly get: TEST PASSED")
		else:
			print("Light paths NOT correctly get: TEST FAILED")

def remove_ligth_path_test(tarc, light_path_uuid):
	response = tarc.del_light_path(light_path_uuid)
	if(response.status==200):
		print("Ligth path correctly removed: TEST PASSED")
	else:
		print("Ligth path NOT correctly removed: TEST FAILED")	


print("Start testing the SDN platform testing.")

print("\n\nThe following functionalities will be tested.")
print("Topology creation")
print("Topology get")
print("Light path provisioning")
print("Light path get (single and all)")
print("Light path removal\n\n")

tarc = TopologyAppRestClient('10.30.8.74',8181)
topology_json = test_topology_creation(tarc)
test_get_topology(topology_json, tarc)
print("Waiting for the device connections.")
time.sleep(5.0)
light_path_uuid = create_light_path_test(tarc, 'prov_req_payload.json')
print("Waiting the path to be provisioned.")
time.sleep(5.0)
get_light_path_test(tarc, light_path_uuid)
get_all_light_paths_test(tarc, light_path_uuid)
remove_ligth_path_test(tarc, light_path_uuid)


