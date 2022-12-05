from netconf_client.connect import connect_ssh
from netconf_client.ncclient import Manager
from netconf_client.ncclient import RPCReply
import sys
import traceback
import time

#TODO get somehow 
ip_sdn_agent = "10.30.8.74"
port_sdn_agent = 830
uname ="openroadm"
pwd = "openroadm"


def discard_changes():
    session = connect_ssh(host=ip_sdn_agent, port=port_sdn_agent, username=uname, password=pwd)
    mgr = Manager(session, timeout=120)
    mgr.discard_changes()
    mgr.close_session()

discard_changes()