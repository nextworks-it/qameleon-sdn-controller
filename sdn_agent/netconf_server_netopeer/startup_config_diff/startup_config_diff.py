import xml.etree.ElementTree as ET
import xmltodict, json
import copy
import sys

ET.register_namespace("", "urn:cesnet:tmc:datastores:file")



#TODO when the file format and the parameter are available, modify the implementation accordingly.		
def get_configuration_from_file_tpa(filename):
	print("Pretending to read from "+filename+" configuration file");
	params = {}
	params["tpa-id"]="144"
	params["port-id"]="414"
	params["n"]="414"
	params["m"]="414"
	print("params:")
	print(params)
	return params


#TODO when the file format and the parameter is available, modify the implementation accordingly.
def get_configuration_from_file_wss(filename):
	print("Pretending to read from "+filename+" configuration file.");
	params = {}
	params["wss-id"]="44"
	params["port-id"]="44"
	params["n"]="45"
	params["m"]="45"
	print("params:")
	print(params)
	return params



def get_startup_datastore_file(datastore_filename, yang_model_name):
	try:
		f = open(datastore_filename, "r")
		data_store_str=f.read()
		datastore_startup_xml = ET.fromstring(data_store_str)
		return datastore_startup_xml

	except OSError:
		print("File "+datastore_filename+ " not available.")
		sys.exit()


def write_conf_file(datastore_startup_xml, object_name):
	#Before writing on the new configuration file, the xml string is cleaned
	xmlstr = ET.tostring(datastore_startup_xml, method='xml').decode('utf8')
	xmlstr="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+xmlstr
	my_str=xmlstr.replace("ns1:","")
	my_str=my_str.replace("<"+object_name+">","<"+object_name+" xmlns=\"http://org/nextworks/qameleon/"+object_name+"\">")
	my_str=my_str.replace(" xmlns:ns1=\"http://org/nextworks/qameleon/"+object_name+"\"","")
	my_str+="\n"
	text_file = open("datastore-"+object_name+".xml", "w")
	n = text_file.write(my_str)
	text_file.close()

def find_diff(datastore_startup_xml, params_from_file, object_name):
	#Cases:
	#1. The configuration parameters are equal. No actions performed.
	#2. The configuration parameters are different:
		#2.1 File available, but no startup datastore 
		#2.2 Different parameters (Generic case)


	root=datastore_startup_xml
	found=False

	for child in root:
		if "startup" in child.tag:
			startup_root=child

	startup_root_tmp = copy.deepcopy(startup_root)
	#TODO There should be a set of minimal configuration that should be available at the start up time
	# Figure out what these are (for tpa, wss and so on) and set it when the below case occurs
	if len(startup_root_tmp.getchildren())==0:#2.1 File available, but no startup datastore 
		print("Startup xml has no child. It means no startup configuration.")
		return


	print(ET.tostring(startup_root_tmp, method='xml').decode('utf8'))
	conf_are_equal=True

	for elem in startup_root_tmp.iter():	
		for key in params_from_file.keys():
			str_tmp=elem.tag.split("}")[1]
			if(key == str_tmp and params_from_file[key]==elem.text):
				print("value of "+key+" are equal:"+elem.text)
			if(key == str_tmp and params_from_file[key]!=elem.text):#Case #2.2
				print("value of "+key+" are different: XML datastore startup value: "+elem.text+ ". Configuration file value: "+params_from_file[key])
				elem.text=params_from_file[key]
				conf_are_equal=False
	
	if(conf_are_equal):#Case #1.
		print("Configuration file not changed. Not going to update datastore file for "+object_name)
		return
		
	
	for child in datastore_startup_xml:
		if "startup" in child.tag:
			datastore_startup_xml.remove(child)
			break;

	datastore_startup_xml.append(startup_root_tmp)		

	write_conf_file(datastore_startup_xml, object_name)


def read_config_file(config_file_name):
	with open(config_file_name) as json_file:
		data = json.load(json_file)
		return data




###############################################TEST FUNCTIONS BELOW##################################

def test_tpa(config):
	object_name="tpa"

	abs_path_conf =config["ext_conf_file"]["abs_path"]
	filename_conf = config["ext_conf_file"][object_name]	
	params_from_file=get_configuration_from_file_tpa(filename_conf)

	abs_path =config["datastores"]["abs_path"]
	filename = config["datastores"][object_name]
	datastore_filename=abs_path+filename

	datastore_startup_xml= get_startup_datastore_file(datastore_filename,object_name)
	find_diff(datastore_startup_xml, params_from_file,object_name)


def test_wss(config):
	object_name="wss"
	abs_path =config["datastores"]["abs_path"]
	filename = config["datastores"][object_name]
	params_from_file=get_configuration_from_file_wss(object_name+"_conf.txt")
	datastore_startup_xml= get_startup_datastore_file(datastore_filename,object_name)
	find_diff(datastore_startup_xml, params_from_file,object_name)

#file_name = "config.json"
#config = read_config_file(file_name)
#test_tpa(config)
#test_wss(config)


