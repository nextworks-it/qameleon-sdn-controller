from netconf_client.connect import connect_ssh
from netconf_client.ncclient import Manager
from netconf_client.ncclient import RPCReply
import sys
import traceback
import time
from flask import Flask, Response, make_response, request
import logging
from flask import json

logging.basicConfig(format='[%(name)s] %(asctime)s - %(message)s', level=logging.DEBUG)
module_name = "NETCONF_CLIENT"
logger = logging.getLogger(module_name)

app = Flask(__name__)
start = True

def set_openroadm_sdn_agent_info(ip_sdn_agent, port, uname, pwd):
    sdn_agent_info = {}
    sdn_agent_info['ip']=ip_sdn_agent
    sdn_agent_info['port']=port
    sdn_agent_info['uname']=uname
    sdn_agent_info['pwd']=pwd
    with open('sdn_agent_info.json', 'w') as outfile:
        outfile.write(json.dumps(sdn_agent_info))

def get_manager():
    data = get_openroadm_sdn_agent_info()
    session = connect_ssh(host=data['ip'], port=int(data['port']), username=data['uname'], password=data['pwd'])
    mgr = Manager(session, timeout=120)
    return mgr

def get_openroadm_sdn_agent_info():
    with open('sdn_agent_info.json') as json_file:
        data = json.load(json_file)
        return data

def setup_mc_ttp_interfaces(name, description, prefix_supporting_interface, circuit_pack, min_freq, max_freq, target_data_store='running'):
    mgr = get_manager()
    
    try:
        mgr.edit_config(target=target_data_store, config='''
        <config> 
            <org-openroadm-device xmlns="http://org/openroadm/device">
            <interface>
                <name>'''+name+'''-TX</name>
                <description>'''+description+'''</description>
                <type>openROADM-if:mediaChannelTrailTerminationPoint</type>
                <administrative-state>inService</administrative-state>
                <supporting-circuit-pack-name>'''+circuit_pack+'''-AMPTX</supporting-circuit-pack-name>
                <supporting-port>'''+circuit_pack+'''-AMPTX-OUT</supporting-port>
                <supporting-interface>'''+prefix_supporting_interface+'''-TX</supporting-interface>         
                <mc-ttp xmlns="http://org/openroadm/media-channel-interfaces">
                    <min-freq>'''+str(min_freq)+'''</min-freq>
                    <max-freq>'''+str(max_freq)+'''</max-freq>
                </mc-ttp>
            </interface>
            <interface>
                <name>'''+name+'''-RX</name>
                <description>'''+description+'''-in</description>
                <type>openROADM-if:mediaChannelTrailTerminationPoint</type>
                <administrative-state>inService</administrative-state>
                <supporting-circuit-pack-name>'''+circuit_pack+'''-AMPRX</supporting-circuit-pack-name>
                <supporting-port>'''+circuit_pack+'''-AMPRX-IN</supporting-port>
                <supporting-interface>'''+prefix_supporting_interface+'''-RX</supporting-interface>
                <mc-ttp xmlns="http://org/openroadm/media-channel-interfaces">
                    <min-freq>'''+str(min_freq)+'''</min-freq>
                    <max-freq>'''+str(max_freq)+'''</max-freq>
                </mc-ttp>
            </interface>
            </org-openroadm-device>
        </config>''', format='xml')
        mgr.close_session()
        return True
    except:
        print("Error during setup of mc ttp interfaces")

        traceback.print_exc()
        mgr.close_session()
        return False 


def remove_mc_ttp_interfaces(name, description, prefix_supporting_interface, circuit_pack, min_freq, max_freq, target_data_store='running'):
    mgr = get_manager()
    try:
        mgr.edit_config(target=target_data_store, config='''
        <config> 
            <org-openroadm-device xmlns="http://org/openroadm/device" xmlns:nc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <interface nc:operation="delete">
                <name>'''+name+'''-TX</name>
                <description>'''+description+'''</description>
                <type>openROADM-if:mediaChannelTrailTerminationPoint</type>
                <administrative-state>inService</administrative-state>
                <supporting-circuit-pack-name>'''+circuit_pack+'''-AMPTX</supporting-circuit-pack-name>
                <supporting-port>'''+circuit_pack+'''-AMPTX-OUT</supporting-port>
                <supporting-interface>'''+prefix_supporting_interface+'''-TX</supporting-interface>         
                <mc-ttp xmlns="http://org/openroadm/media-channel-interfaces">
                    <min-freq>'''+str(min_freq)+'''</min-freq>
                    <max-freq>'''+str(max_freq)+'''</max-freq>
                </mc-ttp>
            </interface>
            <interface nc:operation="delete">
                <name>'''+name+'''-RX</name>
                <description>'''+description+'''-in</description>
                <type>openROADM-if:mediaChannelTrailTerminationPoint</type>
                <administrative-state>inService</administrative-state>
                <supporting-circuit-pack-name>'''+circuit_pack+'''-AMPRX</supporting-circuit-pack-name>
                <supporting-port>'''+circuit_pack+'''-AMPRX-IN</supporting-port>
                <supporting-interface>'''+prefix_supporting_interface+'''-RX</supporting-interface>
                <mc-ttp xmlns="http://org/openroadm/media-channel-interfaces">
                    <min-freq>'''+str(min_freq)+'''</min-freq>
                    <max-freq>'''+str(max_freq)+'''</max-freq>
                </mc-ttp>
            </interface>
            </org-openroadm-device>
        </config>''')
        mgr.close_session()
        return True
    except:
        print("Error removing mc ttp interfaces")

        traceback.print_exc()
        mgr.close_session()
        return False

def setup_nmc_ctp_interfaces(name_prefix, description, prefix_supporting_interface, circuit_pack, freq, width, target_data_store='running'):
    mgr = get_manager()
    try:
        mgr.edit_config(target=target_data_store, config='''
        <config> 
            <org-openroadm-device xmlns="http://org/openroadm/device">
            <interface>
                <name>'''+name_prefix+'''-RX-'''+str(freq)+'''</name>
                <description>'''+description+'''-out</description>
                <type>openROADM-if:networkMediaChannelConnectionTerminationPoint</type>
                <administrative-state>inService</administrative-state>
                <supporting-circuit-pack-name>'''+circuit_pack+'''-AMPTX</supporting-circuit-pack-name>
                <supporting-interface>'''+prefix_supporting_interface+'''-TX</supporting-interface>
                <supporting-port>'''+circuit_pack+'''-AMPTX-OUT</supporting-port>
                <nmc-ctp xmlns="http://org/openroadm/network-media-channel-interfaces">
                    <frequency>'''+str(freq)+'''</frequency>
                    <width>'''+str(width)+'''</width>
                </nmc-ctp>
            </interface>
            <interface>
                <name>'''+name_prefix+'''-TX-'''+str(freq)+'''</name>
                <description>'''+description+'''-in</description>
                <type>openROADM-if:networkMediaChannelConnectionTerminationPoint</type>
                <administrative-state>inService</administrative-state>
                <supporting-circuit-pack-name>'''+circuit_pack+'''-AMPRX</supporting-circuit-pack-name>
                <supporting-interface>'''+prefix_supporting_interface+'''-RX</supporting-interface>
                <supporting-port>'''+circuit_pack+'''-AMPRX-IN</supporting-port>
                <nmc-ctp xmlns="http://org/openroadm/network-media-channel-interfaces">
                    <frequency>'''+str(freq)+'''</frequency>
                    <width>'''+str(width)+'''</width>
                </nmc-ctp>
            </interface>
            </org-openroadm-device>
        </config>''')
        mgr.close_session()
        return True
        
    except:
        print("Error setup of NMC CTP interfaces")
        traceback.print_exc()
        mgr.close_session()
        return False

def remove_nmc_ctp_interfaces(name_prefix, description, prefix_supporting_interface, circuit_pack, freq, width, target_data_store='running'):
    mgr = get_manager()
    try:
        mgr.edit_config(target=target_data_store, config='''
        <config> 
            <org-openroadm-device xmlns="http://org/openroadm/device" xmlns:nc="urn:ietf:params:xml:ns:netconf:base:1.0">
            <interface nc:operation="delete">
                <name>'''+name_prefix+'''-RX-'''+str(freq)+'''</name>
                <description>'''+description+'''-out</description>
                <type>openROADM-if:networkMediaChannelConnectionTerminationPoint</type>
                <administrative-state>inService</administrative-state>
                <supporting-circuit-pack-name>'''+circuit_pack+'''-AMPTX</supporting-circuit-pack-name>
                <supporting-interface>'''+prefix_supporting_interface+'''-TX</supporting-interface>
                <supporting-port>'''+circuit_pack+'''-AMPTX-OUT</supporting-port>
                <nmc-ctp xmlns="http://org/openroadm/network-media-channel-interfaces">
                    <frequency>'''+str(freq)+'''</frequency>
                    <width>'''+str(width)+'''</width>
                </nmc-ctp>
            </interface>
            <interface nc:operation="delete">
                <name>'''+name_prefix+'''-TX-'''+str(freq)+'''</name>
                <description>'''+description+'''-in</description>
                <type>openROADM-if:networkMediaChannelConnectionTerminationPoint</type>
                <administrative-state>inService</administrative-state>
                <supporting-circuit-pack-name>'''+circuit_pack+'''-AMPRX</supporting-circuit-pack-name>
                <supporting-interface>'''+prefix_supporting_interface+'''-RX</supporting-interface>
                <supporting-port>'''+circuit_pack+'''-AMPRX-IN</supporting-port>
                <nmc-ctp xmlns="http://org/openroadm/network-media-channel-interfaces">
                    <frequency>'''+str(freq)+'''</frequency>
                    <width>'''+str(width)+'''</width>
                </nmc-ctp>
            </interface>
            </org-openroadm-device>
        </config>''')
        mgr.close_session()
        return True
        
    except:
        print("Error removing NMC CTP interfaces")
        traceback.print_exc()
        mgr.close_session()
        return False

def create_roadm_connection(degSrc, degDst, freq, target_data_store='running'):
            src = '''NMC-CTP-'''+degSrc+'''-RX-'''+str(freq)
            dst = '''NMC-CTP-'''+degDst+'''-TX-'''+str(freq)
            
            config = '''<config><org-openroadm-device xmlns="http://org/openroadm/device">
                <roadm-connections>
                    <connection-name>'''+src+'''-to-'''+dst+'''</connection-name>
                    <opticalControlMode>off</opticalControlMode>
                    <target-output-power>0</target-output-power>
                    <source>
                        <src-if>'''+src+'''</src-if>
                    </source>
                    <destination>
                        <dst-if>'''+dst+'''</dst-if>
                    </destination>
                </roadm-connections>
            </org-openroadm-device></config> '''
            mgr = get_manager()
            try:
                mgr.edit_config(target=target_data_store, config=config)
                mgr.close_session()
                return True

            except:
                print("Error setup roadm connection")
                traceback.print_exc()
                mgr.close_session()
                return False

def remove_roadm_connection(degSrc, degDst, freq, target_data_store='running'):
            src = '''NMC-CTP-'''+degSrc+'''-RX-'''+str(freq)
            dst = '''NMC-CTP-'''+degDst+'''-TX-'''+str(freq)
            config=  '''
                    <config>
                    <org-openroadm-device xmlns="http://org/openroadm/device" xmlns:nc="urn:ietf:params:xml:ns:netconf:base:1.0">
                    <roadm-connections nc:operation="delete">
                        <connection-name>'''+src+'''-to-'''+dst+'''</connection-name>
                        <opticalControlMode>off</opticalControlMode>
                        <target-output-power>0</target-output-power>
                        <source>
                            <src-if>'''+src+'''</src-if>
                        </source>
                        <destination>
                            <dst-if>'''+dst+'''</dst-if>
                        </destination>
                    </roadm-connections>
                </org-openroadm-device>
                </config>''' 
            mgr = get_manager()
            try:
                mgr.edit_config(target=target_data_store, config=config)
                mgr.close_session()
                return True

            except:
                print("Error removing roadm connection")
                traceback.print_exc()
                mgr.close_session()
                return False

def is_interface_existing(interface_name, target_data_store='running'):
        get_config_filter = '''<org-openroadm-device xmlns="http://org/openroadm/device">
        <interface>
                <name>'''+interface_name+'''</name>
        </interface>
        </org-openroadm-device>'''
        mgr = get_manager()
        try:
            response = mgr.get_config(source=target_data_store, filter=get_config_filter)
            root = response.data_ele
            print(root)
            elements_found = root.findall("{http://org/openroadm/device}org-openroadm-device/{http://org/openroadm/device}interface")
            for element in elements_found:
                print(element)
                for entry in element:
                    key = remove_namespace_from_tag(entry.tag)
                    if(key=="name" and entry.text==interface_name):
                        mgr.close_session()
                        return True
            mgr.close_session()
            return False

        except:
            print("Error setup roadm connection interfaces")
            traceback.print_exc()
            mgr.close_session()
            return False

def remove_namespace_from_tag(tag_str):
    if("}" in tag_str):
                tag_str = tag_str.split("}")[1]
    return tag_str

def get_interface_info_from_response(interface_name, root_element_xml):
    all_ifs = root_element_xml.findall("{http://org/openroadm/device}org-openroadm-device/{http://org/openroadm/device}interface")
    response_json = {}
    index = -1
    is_nmc_ctp = False
    for i in range(0, len(all_ifs)):
        single_if = all_ifs[i]
        for entry in single_if:
            key = remove_namespace_from_tag(entry.tag)
            if(key=="name" and entry.text==interface_name):
                index = i
                
            if(key=="type" and entry.text=="openROADM-if:networkMediaChannelConnectionTerminationPoint"):
                is_nmc_ctp=True     
    if(index==-1):
        return {}
    if(is_nmc_ctp==True):
        tag_to_search = "nmc-ctp"

    if_found = all_ifs[index]
    response_json[interface_name] = {}
    for el in if_found:
        key = remove_namespace_from_tag(el.tag)
        
        if(key!=tag_to_search):
            response_json[interface_name][key]=el.text
        else:
            response_json[interface_name][key]={}
        if(is_nmc_ctp==True and tag_to_search==key):
            for subel in el:
                subkey = remove_namespace_from_tag(subel.tag)
                response_json[interface_name][key][subkey] = subel.text

    return response_json[interface_name]

def get_connection_info_from_response(connection_name, root_element_xml):
    all_conns = root_element_xml.findall("{http://org/openroadm/device}org-openroadm-device/{http://org/openroadm/device}roadm-connections")
    connection_json = {}
    index = -1
    is_nmc_ctp=False
    for i in range(0, len(all_conns)):
        conn = all_conns[i]
        for el in conn:
            key = remove_namespace_from_tag(el.tag)
            if(key=="connection-name" and el.text==connection_name):
                index = i
                connection_json[connection_name]={}
                break
            
    if(index==-1):
        return {}

    connection = all_conns[i]            
    for el in connection:
        key = remove_namespace_from_tag(el.tag)
        connection_json[connection_name][key]=el.text
        if(key=="source" or key=="destination"):
            for subel in el:
                subkey = remove_namespace_from_tag(subel.tag)
                connection_json[connection_name][key] = {}
                connection_json[connection_name][key][subkey] = subel.text
    return connection_json[connection_name]
            
def get_connections_info_from_response(root_element_xml):
    all_conns = root_element_xml.findall("{http://org/openroadm/device}org-openroadm-device/{http://org/openroadm/device}roadm-connections")
    connections_json = []
    for i in range(0, len(all_conns)):
        conn = all_conns[i]
        connection_json = {}
        connection_name = None
        for el in conn:
            key = remove_namespace_from_tag(el.tag)
            if(key=="connection-name"):
                connection_name = el.text
                connection_json[connection_name]={}
            connection_json[connection_name][key]= el.text
            if(key=="source" or key=="destination"):
                for subel in el:
                    subkey = remove_namespace_from_tag(subel.tag)
                    connection_json[connection_name][key] = {}
                    connection_json[connection_name][key][subkey]= subel.text
        connections_json.append(connection_json)
    return connections_json
                    
@app.route('/open-roadm/interface/<interface_name>', methods=['GET'])
def get_interface_info(interface_name=None):
    get_config_filter = None
    interface_name = request.view_args['interface_name']
    
    if(interface_name!=None):
        mgr = get_manager()
        try:
            net_conf_response = mgr.get_config(source='running')
            root = net_conf_response.data_ele
            
            body_response = get_interface_info_from_response(interface_name, root)
            if(len(body_response)==0):
                response = Response()
                response.status_code = 404
                mgr.close_session()
                return response
            
            r = Response(response=json.dumps(body_response), status=200, mimetype="application/json")
            mgr.close_session()
            return r

        except:
            traceback.print_exc()
            response = Response()
            response.status_code = 400
            mgr.close_session()
        return response

@app.route('/open-roadm/connection', methods=['GET'])
def get_connections_info():
    
    mgr = get_manager()
    try:
        net_conf_response = mgr.get_config(source='running')
        root = net_conf_response.data_ele
        body_response = get_connections_info_from_response(root)
    
        r = Response(response=json.dumps(body_response), status=200, mimetype="application/json")
        mgr.close_session()
        return r

    except:
        traceback.print_exc()
        response = Response()
        response.status_code = 400
    mgr.close_session()

    return response

@app.route('/open-roadm/connection/<connection_name>', methods=['GET'])
def get_connection_info(connection_name=None):
    connection_name = request.view_args['connection_name']
    
    if(connection_name!=None):
        mgr = get_manager()
        try:
            net_conf_response = mgr.get_config(source='running')
            root = net_conf_response.data_ele
            body_response = get_connection_info_from_response(connection_name, root)
            if(len(body_response)==0):
                response = Response()
                response.status_code = 404
                mgr.close_session()
                return response
            
            r = Response(response=json.dumps(body_response), status=200, mimetype="application/json")
            mgr.close_session()
            return r

        except:
            traceback.print_exc()
            response = Response()
            response.status_code = 400
        mgr.close_session()

        return response


@app.route('/open-roadm/express-link', methods=['POST'])
def create_express_link_open_roadm():

    json_data = request.get_json()
    deg_src=json_data['degSrc']
    deg_dst=json_data['degDst']
    start_freq=json_data['startFreq']
    end_freq=json_data['endFreq']
    freq_width = json_data['freqWidth']

    response = Response()
    response.status_code = 201

    central_freq = (start_freq + end_freq) /2
    central_freq = str(central_freq)
    mc_ttp_name_src = "MC-TTP-"+central_freq+"-Thz-"+deg_src
    mc_ttp_name_dst = "MC-TTP-"+central_freq+"-Thz-"+deg_dst
    oms_interface_src = "OMS-"+deg_src+"-TTP"
    oms_interface_dst = "OMS-"+deg_dst+"-TTP"
    oms_deg_src_rx_existing = is_interface_existing(oms_interface_src+"-RX")
    oms_deg_src_tx_existing = is_interface_existing(oms_interface_src+"-TX")
    oms_deg_dst_rx_existing = is_interface_existing(oms_interface_dst+"-RX")
    oms_deg_dst_tx_existing = is_interface_existing(oms_interface_dst+"-TX")

    setup_mc_ttp_if_src_ok = False
    setup_mc_ttp_if_dst_ok = False
    if(oms_deg_src_rx_existing and oms_deg_src_tx_existing):
        print("### Setting up MC TTP interfaces...")
        setup_mc_ttp_if_src_ok = setup_mc_ttp_interfaces(mc_ttp_name_src,"Media-Channel-TTP-"+central_freq+"Thz-"+deg_src,"OMS-"+deg_src+"-TTP", deg_src, start_freq, end_freq)
        setup_mc_ttp_if_dst_ok = setup_mc_ttp_interfaces(mc_ttp_name_dst,"Media-Channel-TTP-"+central_freq+"Thz-"+deg_dst,"OMS-"+deg_dst+"-TTP", deg_dst, start_freq, end_freq)
    
    if(not setup_mc_ttp_if_src_ok or not setup_mc_ttp_if_dst_ok):
         print("Error setting up MC TTP interfaces on ROADM devices")
         print(setup_mc_ttp_if_src_ok)
         print(setup_mc_ttp_if_dst_ok)
         response.status_code = 400
         return response

    print("\nMC TTP interface successfully setup ")

    setup_nmc_ctp_if_src_ok = False
    setup_nmc_ctp_if_dst_ok = False

    if(oms_deg_dst_rx_existing and oms_deg_dst_tx_existing):
        print("### Setting up NMC CTP interfaces...")
        setup_nmc_ctp_if_src_ok =setup_nmc_ctp_interfaces("NMC-CTP-"+deg_src, "Network-Media-Channel-CTP-"+central_freq+"THz-"+deg_src, mc_ttp_name_src, deg_src, central_freq, freq_width)
        setup_nmc_ctp_if_dst_ok =setup_nmc_ctp_interfaces("NMC-CTP-"+deg_dst, "Network-Media-Channel-CTP-"+central_freq+"THz-"+deg_dst, mc_ttp_name_dst, deg_dst, central_freq, freq_width)
    
    if(not setup_nmc_ctp_if_src_ok or not setup_nmc_ctp_if_dst_ok):
        print("Error setting up NMC TTP interfaces on ROADM devices")
        print(setup_nmc_ctp_if_src_ok)
        print(setup_nmc_ctp_if_dst_ok)
        response.status_code = 400
        return response

    print("\nNMC CTP interface successfully setup ")
    print("### Setting up ROADM Connection...")
    res_roadm_connection_setup_ok = create_roadm_connection(deg_src, deg_dst, central_freq)
    if(not res_roadm_connection_setup_ok):
        response.status_code = 400

    return response


@app.route('/open-roadm/express-link', methods=['DELETE'])
def remove_express_link_open_roadm():
    json_data = request.get_json()
    deg_src=json_data['degSrc']
    deg_dst=json_data['degDst']
    start_freq=json_data['startFreq']
    end_freq=json_data['endFreq']
    freq_width = json_data['freqWidth']

    response = Response()
    response.status_code = 204

    central_freq = (start_freq + end_freq) /2
    central_freq = str(central_freq)
    ##Step #1: Removing ROADM connection
    print("Removing ROADM Connection...")
    res_roadm_connection_setup_ok = remove_roadm_connection(deg_src, deg_dst, central_freq)
    if(not res_roadm_connection_setup_ok):
        print("ROADM Connection cannot be removed.")

    ## Step #2: Removing NMC-CTP interfaces
    mc_ttp_name_src = "MC-TTP-"+central_freq+"-Thz-"+deg_src
    mc_ttp_name_dst = "MC-TTP-"+central_freq+"-Thz-"+deg_dst
    print("Removing NMC-CTP interfaces...")
    setup_nmc_ctp_if_src_ok =remove_nmc_ctp_interfaces("NMC-CTP-"+deg_src, "Network-Media-Channel-CTP-"+central_freq+"THz-"+deg_src, mc_ttp_name_src, deg_src, central_freq, freq_width)
    setup_nmc_ctp_if_dst_ok =remove_nmc_ctp_interfaces("NMC-CTP-"+deg_dst, "Network-Media-Channel-CTP-"+central_freq+"THz-"+deg_dst, mc_ttp_name_dst, deg_dst, central_freq, freq_width)
    if(not setup_nmc_ctp_if_src_ok or not setup_nmc_ctp_if_dst_ok):
        print("Error removing NMC TTP interfaces from  ROADM devices")
        print(setup_nmc_ctp_if_src_ok)
        print(setup_nmc_ctp_if_dst_ok)
    else:    
        print("\nNMC CTP interface successfully removed ")
    
    ## Step #3: Removing MC-TTP interfaces
    print("Removing MC-TTP interfaces...")
    setup_mc_ttp_if_src_ok = remove_mc_ttp_interfaces(mc_ttp_name_src,"Media-Channel-TTP-"+central_freq+"Thz-"+deg_src,"OMS-"+deg_src+"-TTP", deg_src, start_freq, end_freq)
    setup_mc_ttp_if_dst_ok = remove_mc_ttp_interfaces(mc_ttp_name_dst,"Media-Channel-TTP-"+central_freq+"Thz-"+deg_dst,"OMS-"+deg_dst+"-TTP", deg_dst, start_freq, end_freq)
    
    if(not setup_mc_ttp_if_src_ok or not setup_mc_ttp_if_dst_ok):
         print("Error removing MC TTP interfaces on ROADM devices")
         print(setup_mc_ttp_if_src_ok)
         print(setup_mc_ttp_if_dst_ok)
         response.status_code = 400
    else:
        print("\nMC TTP interface successfully removed ")

    return response


@app.route('/open-roadm/config', methods=['POST'])
def config_open_roadm_proxy():
    response = Response()
    try:
        json_data = request.get_json()
        print("Received request to configure the server")
        # if(json_data['username']=="null"):
        #     json_data['username']="openroadm"
        # if(json_data['password']=="null"):
        #     json_data['password']="openroadm"    
        set_openroadm_sdn_agent_info(json_data['ip'],json_data['port'], json_data['username'], json_data['password'])
        response.status_code = 201
    except:
        response.status_code = 400
        traceback.print_exc()
    return response

@app.route('/open-roadm/discard-changes', methods=['POST'])
def discard_changes():
    mgr = get_manager()
    response = Response()
    try:
        mgr.discard_changes()
        response.status_code = 201
        
    except:
        response.status_code = 400

    mgr.close_session()
    return response

    



app.run(host='0.0.0.0', port=8080, debug=True)