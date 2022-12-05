#cd /usr/src/netopeer/startup_config_diff;
#nohup python3 startup_config_diff.py &
#cd /usr/src/netopeer/socket_server
#nohup python3 /usr/src/netopeer/socket_server/socket_server.py &
netopeer-server -v 7
