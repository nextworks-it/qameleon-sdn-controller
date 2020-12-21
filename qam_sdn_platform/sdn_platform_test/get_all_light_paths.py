import odlHttpClient as ohc
import sys, json
from qam_topology_app_client import TopologyAppRestClient

custom_tarc = TopologyAppRestClient('10.30.8.74',8181)
response = custom_tarc.get_all_light_paths()
parsed = json.loads(response.read().decode("utf-8"))
print(json.dumps(parsed, indent=4, sort_keys=True))
