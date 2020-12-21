import odlHttpClient as ohc
from qam_topology_app_client import TopologyAppRestClient

custom_tarc = TopologyAppRestClient('10.30.8.74',8181)
topology = custom_tarc.create_topology('topology_real_nodes.json')
#top = custom_tarc.get_topology()
#print(top)
#custom_tarc.disable_node("finisar")
#custom_tarc.disable_port("fake831","OUT01")

