FROM ubuntu:18.04
##Installing Java 11 for compatibility reasons, python3 and pip
RUN apt update && apt-get install -y openjdk-11-jdk && rm -rf /var/lib/apt/lists/*
RUN apt-get update && apt-get install -y software-properties-common gcc && \
    add-apt-repository -y ppa:deadsnakes/ppa
RUN apt-get update && apt-get install -y python3.6 python3-distutils python3-pip python3-apt python-pip
RUN pip3 install --upgrade pip
RUN pip3 install netconf-client
RUN pip install flask

## Copying all the built files for running the QAM SDN Controller
RUN mkdir app-wrapper
WORKDIR app-wrapper
COPY qam_sdn_platform/topology_app .
COPY qam_sdn_platform/provisioning_plugin/target/provisioning-plugin-0.0.1.jar ./topology_app/karaf/target/assembly/deploy/provisioning-plugin-0.0.1.jar
COPY qam_sdn_platform/provisioning_app/target/provisioning-app-0.1.0.jar ./topology_app/karaf/target/assembly/deploy/provisioning-app-0.1.0.jar

#Copying configuration files and scripts
COPY qam_sdn_platform/topology_app/controller.properties_docker ./controller.properties
COPY sdn_agent/netconf_server_netopeer/inv_lookup_table.json ./topology_app/inv_lookup_table.json
RUN mkdir ./openroadm-netconf-plugin
COPY qam_sdn_platform/netconf-drivers/openroadm-netconf-plugin ./openroadm-netconf-plugin
COPY startup.sh startup.sh

#RUN keytool -import -v -trustcacerts -alias serveralias -file ./cacert.pem -keypass ubuntu -keystore /cacerts -noprompt
EXPOSE 8181
CMD bash startup.sh
