#1. Clone netopeer remote repository
git clone https://github.com/CESNET/netopeer

#2. Replace Dockerfile into netopeer dir, with Dockerfile in this dir
cp Dockerfile netopeer/Dockerfile

#3. Copy the startup configuration checker into netopeer dir.
cp -r ../netconf_server_netopeer/startup_config_diff netopeer/
cp ../netconf_server_netopeer/startup_config_diff/config_docker.json netopeer/startup_config_diff/config.json

#4. Copy the netopeer libs developed (wss and tpa for now). The docker file will build and embed them into ithe netopeer serve
cp -r ../netconf_server_netopeer/netopeer_libs/ netopeer/
chmod 777 wrapper.sh
cp wrapper.sh netopeer/

#5. Copy the socket server that will receive and parse the xml request coming from the SDN controller.
mkdir netopeer/socket_server
cp ../netconf_server_netopeer/socket_server.py netopeer/socket_server
cp ../netconf_server_netopeer/inv_lookup_table.json netopeer/inv_lookup_table.json

## 6. Build docker file
cd netopeer
sudo docker build -t netopeer_server:0.1 .

## 7. Starting socket server
#sudo docker exec -it a4f8cb22a085 bash -c 'nohup python3 /usr/src/netopeer/socket_server.py &> output.out & sleep 1'

## 8. Show the just built image
sudo docker image ls

echo "Run the container with: sudo docker run -d -v <host_path_where_conf_file_are_stored>:/home/cfg_files -p 830:830 <image_id>"
echo "Example on Linux machine: sudo docker run -d -v /home/ubuntu/shared:/home/cfg_files -p 830:830 14d8a279de88"
echo "Example on Windows machine: sudo docker run -d -v C:\Users\ktok\Documents\MATLAB\agent_commands.txt:/home/cfg_files -p 830:830 14d8a279de88"

echo ""
echo "In order to start the socket server: execute sudo docker exec -it <container_id> bash -c 'nohup python3 /usr/src/netopeer/socket_server.py &> output.out & sleep 1' "
