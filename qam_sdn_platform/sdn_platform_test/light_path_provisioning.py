import odlHttpClient as ohc
from qam_topology_app_client import TopologyAppRestClient
import json

custom_tarc = TopologyAppRestClient('10.30.8.74',8181)
response = custom_tarc.create_light_path('prov_req_payload.json')
parsed = json.loads(response.read().decode("utf-8"))
print(json.dumps(parsed, indent=4, sort_keys=True))
