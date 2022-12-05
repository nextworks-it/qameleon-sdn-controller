import socket
import xml.etree.ElementTree as ET
import xmltodict, json
import sys
from startup_config_diff import startup_config_diff as scd

HOST = '127.0.0.1'  # Standard loopback interface address (localhost)
PORT = 12345        # Port to listen on (non-privileged ports are > 1023)
BUFFER_SIZE= 10000

datastore_collector = {}

DUMMY="dummy"
WSS="wss"
SBVT="sbvt"


def write_on_file(filename, value):
	print("Going to write on file "+filename+" the value to write on file "+str(value))
	try:
		f = open(filename,"w")
		f.write(str(value))
		f.close()
		return True

	except Exception as e:
		print(e)
		return False

def is_dummy(json):
	if DUMMY in json:
		return True
	return False

def is_sbvt(json):
	if SBVT in json:
		return True
	return False

def is_wss(json):
	if WSS in json:
		return True
	return False

def get_value(variable):
	try:
		variable = variable["#text"]
		print("Getting the default value")
		return variable
	except:
		return variable


def get_wavelength_nm(frequency_ghz):
	light_speed_ms=299792458
	wave_length_nm=round(float(light_speed_ms/frequency_ghz),2)
	print(str(frequency_ghz)+ " Ghz corresponds to "+str(wave_length_nm)+" nm. (Light speed "+str(light_speed_ms)+" m/s)")
	return wave_length_nm



def get_channel_number(wave_length_nm):
	f = open("/usr/src/netopeer/inv_lookup_table.json","r")
	inv_lookup_table = f.read()
	inv_lookup_table = json.loads(inv_lookup_table)
	channel = None
	min=1
	inv_lookup_table = inv_lookup_table[0]
	nearest_wave_length = -1
	found = False
	max_diff_nm = 1
	for wave_length_nm_entry in inv_lookup_table:
		if(wave_length_nm==wave_length_nm_entry):
			print("Found entry into lookup table");
			return inv_lookup_table[wave_length_nm]
		abs_value = round(abs(float(wave_length_nm)-float(wave_length_nm_entry)),4)
		if(abs_value<max_diff_nm and abs_value<min):
			if(abs_value<min):
				found = True
				min=abs_value
				nearest_wave_length = wave_length_nm_entry
				channel = inv_lookup_table[wave_length_nm_entry]
	if(found==False):
		print("Not able to find wavelength within "+str(max_diff_nm)+" nm")
		return channel

	print("The nearest wavelength is "+str(nearest_wave_length)+" nm (Absolute difference is "+str(min)+"). The corresponding channel is "+str(channel))
	return channel





def process_request(json):
	print("JSON to be processed")
	print(json)
	if(is_dummy(json)): ##This if branch is just for testing
		value=int(datastore_collector[DUMMY]["dummy-leaf-int"])
		if(value==None):
			print("No value found")
			return False

		return write_on_file(get_config(DUMMY), value)

	if(is_sbvt(json)):
		base_central_freq=datastore_collector[SBVT]["optical-flows"]["optical-flow-carriers"]["frequency-info"]["base-central-frequency"] #There will be either the default or a custom value
		value = get_value(base_central_freq)
		central_freq_slot_gran=datastore_collector[SBVT]["optical-flows"]["optical-flow-carriers"]["frequency-info"]["central-frequency-slot-granularity"]
		central_freq_slot_gran = float(get_value(central_freq_slot_gran))
		
		channel_number = get_channel_number(get_wavelength_nm(int(value)))
		print("channel number is: "+str(channel_number))
		print("Central frequency slot granularity is: "+str(central_freq_slot_gran))
		
		if(channel_number==None):
			print("Unable to find into inv lookup table the channel number for frequency "+base_central_freq)
			return False

		return write_on_file(get_config(SBVT), channel_number)

	if(is_wss(json)):
		wss_json = datastore_collector[WSS]
		wss_ports_json = wss_json["wss-ports"]
		port_id = wss_ports_json["port-id"]
		base_central_freq = wss_ports_json["port-frequency-info"]["base-central-frequency"]["#text"]
		central_freq_slot_gran = wss_ports_json["port-frequency-info"]["central-frequency-slot-granularity"]["#text"]
		channel_number = get_channel_number(get_wavelength_nm(int(base_central_freq)))
		to_write_into_file=channel_number+" "+central_freq_slot_gran+" "+port_id
		return write_on_file(get_config(WSS), to_write_into_file)

	print("Unable to process the request. Accepted dummy, sbvt ans wss XML bodies")
	return False


def get_config(model):
	config = scd.read_config_file("/usr/src/netopeer/startup_config_diff/config.json");
	abs_path = config["ext_conf_file"]["abs_path"]
	filename = abs_path + config["ext_conf_file"][model]
	return filename


def convert_to_json(data):
	str_data=data.decode("utf-8")
	#print("raw data: "+str_data);
	try:
		json_object = xmltodict.parse(str_data)
		print(json.dumps(json_object, indent=4, sort_keys=True))
		conf_name=list(json_object.keys())[0]
		print("conf name is "+conf_name)
		datastore_collector[conf_name]=json_object[conf_name]
		#print(json.dumps(datastore_collector, indent=4, sort_keys=True))
		return json_object
	except Exception as e:
		print(e)
		print("Unable to convert data into JSON");
		return None



def start_socket_server():
	request_counter = 0
	# Create a TCP/IP socket
	sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

	# Bind the socket to the address given on the command line
	server_name = HOST
	server_address = (server_name, PORT)
	print("Starting socket server at port "+str(PORT))
	sock.bind(server_address)
	sock.listen(1)

	while True:
		try:
			print ("Waiting for a connection")
			connection, client_address = sock.accept()
			try:
				request_counter+=1
				print("client connected "+str(client_address))
				while True:
					data = connection.recv(BUFFER_SIZE)
					print(str(sys.getsizeof(data))+" bytes received.");
					if data:
						print("Request #"+str(request_counter))
						json = convert_to_json(data)
						if(json==None):
							break
						process_request(json)
						break;
			finally:
				connection.close()

		except KeyboardInterrupt:
			print("Keyboard interrupt. Closing socket.")
			sock.close()
			break;


start_socket_server()
