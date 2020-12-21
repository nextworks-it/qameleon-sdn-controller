import odlHttpClient as ohc
import sys, json
from qam_topology_app_client import TopologyAppRestClient

custom_tarc = TopologyAppRestClient('10.30.8.74',8181)
if(len(sys.argv)<2):
	print("Insert light path id")
else:
	response = custom_tarc.del_light_path(sys.argv[1])
	print("Response status: " +str(response.status))
