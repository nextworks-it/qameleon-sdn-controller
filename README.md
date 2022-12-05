[![N|Solid](https://ict-qameleon.eu/wp-content/uploads/2018/02/logo-site3.png)](https://nodesource.com/products/nsolid)

# QAMeLeon SDN Controller  

## Introduction
The **QAMeLeon SDN Controller** has been designed, developed, tested and documented within the scope of **[QAMeLeon project](https://ict-qameleon.eu/)**.  It briefly consists of an SDN Controller that through a disaggregated approach, can configure different types of devices, regardless of the technology used, the vendor, and the information model implemented.
It mainly consists of the Topology and Provisioning Application for the management of a network and the lightpaths in a(n) (optical) network, respectively. Moreover, an external software component called **Path Computation Engine (PCE)** is in charge of computing the path from a source to a destination during the lightpath provisioning request.
The QAMeleon SDN Controller and the PCE expose their functionalities through REST APIs based on [TAPI](https://github.com/OpenNetworkingFoundation/TAPI) specification. More details about the **SDN Controller** can be found in the public Deliverables of the QAMeleon Project.

The purpose of this **README** is to illustrate the procedure for installing and using the QAMeleon SDN Controller. However, some files are not included in the repository because are proprietary.

Briefly, this repository contains: 
- early Software Prototype of the **QAMeleon SDN Controller**, based on the [OpenDaylight Sodium SR2](https://www.opendaylight.org/) framework. 
- early implementation of a **PCE**, a Spring boot server for computing the path from a source to a destination.
- **different emulated optical network and devices** deployable using the correspsonding docker-compose files (more details below)
- A **Postman Collection** for interacting with the QAMeLeon SDN Controller.

In detail, in the root of the repository are available the following files and directories:
- the *PCE* directory, that contains the source code of the PCE.
- *qamelen_sdn_platform* directory, where the source code of the different applications of QAMeleon SDN Controller are available  (this point is better described in **Installation** section) 
- *sdn_agent* directory, containing the source code of the SDN Agent using with the integration with the NLL and WSS.
- *Dockerfile* for building the QAMeleon SDN Controller as docker image  
- *build_qam_sdn_docker_image.sh* for building the QAMeleon SDN Controller from the scratch to a Docker image
- Different docker-compose files (See Usage section for this part)
- *mock_sbvt_env.json* file, a Mockoon enviroment for simulating the SBVT server behaviour 
- *README.md*, this file you are reading
- *org.ops4j.pax.logging.cfg*, for configuring the QAMeleon SDN Controller file

# Requirements
### Hardware requirements
The  minimum hardware requirements are the following: 
- 8 vCPU
- 8GB RAM
- 50GB disk space
### Software requirements
The software components of SDQN Controller and the PCE need the following software requirements:
- Java 11 version
- maven 3.6 version
- Docker version 20.10.12, build e91ed57
- docker-compose version 1.29.2, build 5becea4c

# Installation 
The installation of the **QAMeleon SDN Platform** requires the installation of the QAMeleon SDN Controller, the PCE and optionally a set of simulated nodes. The installation procedure of these component is described in the following subsections. 
### SDQN Controller installation
Below are detailed the steps for creating the SDQN Controller Docker image:
1. Starting from the root of the repository, move to the api dir with:
`cd qam_sdn_platform/topology_app/api/`

2. Then, to compile the source code of the API:
`mvn clean install -DskipTests=true -Dcheckstyle.skip -Dmaven.javadoc.skip=true`
3. This process requires from one to two minutes, especially if it is the first time of compiling the code. After a succesful compilation, move back to the root of repository with:
`cd -`
4. Make sure to have execution permission on the **build_qam_sdn_docker_image.sh** file and build the docker image of the QAMeleon SDN Controller executing it. This process requires a while, especially for the first time. 
`./build_qam_sdn_docker_image.sh`

### PCE installation
Below are detailed the steps for installing the PCE as Docker image:
1. Starting from the root of the repository, move to pce dir with:
`cd PCE`

2. Then, to compile the PCE source code:
`mvn clean install -DskipTests=true -Dcheckstyle.skip -Dmaven.javadoc.skip=true`

4. In this directory, build the docker image of the PCE with:
`sudo docker build . -t pce:0.0.1`

### SDN Agent installation
Below are detailed the steps for installing the PCE as Docker image:
1. Starting from the root of the repository, move to pce dir with:
`cd sdn_agent/docker`

2. Then, execute the following script to install the SDN Agent as docker image. It takes a few minutes, especially if is the first time to be installed.
`dockerize_netconf_server.sh`

### Simulated devices installation
Below are detailed the steps for installing multiple simulated devices as single Docker image:
1. Starting from the root of the repository, move to sim_devices dir with:
`cd sim_devices`

2. Then, build the container with:
`sudo docker build . -t netconf-testtool:1.7.2`


### Installation verification
Eventually, Check if the docker images have been correctly created with:
`sudo docker image ls`

The output should contains something similar the line below (the IMAGE ID is indicative, while size do not):
```
REPOSITORY           TAG       IMAGE ID       CREATED       SIZE
pce                  0.0.1    ffffff6a1b65   3 days ago    476MB
qam_sdn_controller   0.0.1    ffffff46615b   6 days ago    1.93GB
netopeer_server      0.1      ffffabcdef12   3 days ago    1GB
netconf-testtool     1.7.2    fff84f3e5a63   3 days ago    466MB
```
# Usage
In the following section is described how to use the QAMeleon SDN Controller along with the PCE and an simulated network. For integrations and testing reasons, different simulated networks have been considered, each of them composed by different network devices the SDN Controller can connect to. For each docker-compose file, is described what kind of simulated network is used.

- `docker-compose.yml`: has one simulated openROADM node and 8 simulated dummy nodes. The aim of using this simulated network is to tests the integration with an OpenROADM device. 
- `docker-compose_end_to_end.yml`: has one SDN Agent simulating the control of a NLL, a simulated transmitter, seven simulated commercial ROADMs and 8 simulated dummy devices. The aim of using this simulated network is to test the SDN Controller against different types of devices. 
- `docker-compose_lumeuntum.yml`: has seven simulated commercial ROADMs and 8 simulated dummy devices. The aim of using this simulated network is to test the SDN Controller against the commercial devices. 
- `docker-compose_wss_qam.yml`: has seven simulated commercial ROADMs and 2 simulated QAMeleon WSS and one simulated OpenROADM device. The aim of using this simulated network is to test the SDN Controller against the WSSs. 

However, is possible to "build" a custom simulated network creating a new docker-compose file including all the necessary containers information as done for the described ones.

### Start QAMeleon SDN Controller and simulated network
1. Chose the corresponding, docker-compose file, to run the whole environment composed of the SDN Controller, the PCE and the simulated network, execute the following command:
`sudo docker-compose -f <docker_compose_file_name> up -d`

2. After some seconds, the all above mentioned components are up and running. With the following command is possible to see the status:
`sudo docker-compose ps`


```
                Name                              Command               State                                           Ports                                         

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

netconf_testtool_1.7.2                 java -jar netconf-testtool ...   Up      0.0.0.0:17830->17830/tcp, 0.0.0.0:17831->17831/tcp, 0.0.0.0:17832->17832/tcp,         

                                                                                0.0.0.0:17833->17833/tcp, 0.0.0.0:17834->17834/tcp, 0.0.0.0:17835->17835/tcp,         

                                                                                0.0.0.0:17836->17836/tcp, 0.0.0.0:17837->17837/tcp                                    

pce                                    java -jar pce-0.0.1.jar          Up      0.0.0.0:1234->1234/tcp                                                                

qam_sdn_controller                     /bin/sh -c bash startup.sh       Up      0.0.0.0:8081->8081/tcp, 0.0.0.0:8181->8181/tcp                                        

qameleon_devel_openroadm_sdn_agent_1   /usr/bin/netopeer-server -v 3    Up      0.0.0.0:832->830/tcp
```
As example, it has been used a network composed by the simulated openROADM devices and other devices. 

## Usage of the QAMeleon SDN Controller
A Postman collection is available for the usage of the QAMeleon SDN Controller within ``the qam_sdn_platform_dir``. The postman collection is structured as follows:
- ``00_Generic requests``, a directory containing the generic requests to the Northbound Interface (NBI) of the QAMeleon SDN Controller
- ``One-to-one integrations``, a directory containing the generic requests for the one-to-one integration with real and simulated devices
- ``Final experiment TIM premises``, a directory containing reqeuests for the end-to-end integrations with real and simulataed devices

1. As an example, the integration one-to-one with a simulated network containing the openROADM device is considered. However, the same line of reasoning can be applied for the other simulated networks. The directory that contains the postman requests is `OpenROADM Integration(TU/e)`.
2. In this dir, first is used the `Create topology TU-E integration (SIM OpenROADM)` request for making the QAMeleon SDN Controller connected to the simulated network, composed of two dummy devices and one openROADM device. 
3. With the aim of creating a lightpath from a source to a destination, then the `Create lightpath` request is sent, for properly configuring the simulated network.
4. To verify the ligthpath created, under the ``00_Generic requests/Provisioning App``directory, a Postman request called ``Get ERO of all lightpaths`` retrieves the **Explicit route Object (ERO)** of all lightpath created. In this particular case, it gets the ERO of the just created lightpath.

The same line of reasoning can be applied to the other devices, bringing up the corresponding environment from the related docker-compose file.

# Authors
Giada Landi, Pietro G. Giardina, Pietro Piscione [Nextworks S.r.l.](http://www.nextworks.it)

# License
This project is licensed under the EPL 1.0 License - see the [LICENSE](https://www.eclipse.org/legal/epl-v10.html) file for details.
