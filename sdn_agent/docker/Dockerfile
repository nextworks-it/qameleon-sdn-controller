##Into docker
FROM centos:7

# 0. Install required packages
RUN ["yum", "install", "-y", "epel-release"]
RUN ["yum", "install", "-y", "git", "make", "libtool", "libxml2-devel", "file", "libxslt-devel", "libssh-devel", "libcurl-devel", "python3", "python-pip", "libxml2-python", "openssh-server", "augeas-devel","nano","screen"]
RUN ["ssh-keygen", "-A"]
RUN ["pip", "install", "pyang"]

# 1. Clone, build and install libnetconf
RUN set -e -x; \
	git clone https://github.com/CESNET/libnetconf.git /usr/src/libnetconf; \
	cd /usr/src/libnetconf; \
	autoreconf --force --install; \
	./configure --prefix='/usr';\
	make; \
	make install; \
	ln -s /usr/lib/pkgconfig/libnetconf.pc /usr/lib64/pkgconfig/

# 2. Build and install netopeer-server
COPY server /usr/src/netopeer/server
RUN set -e -x; \
	cd /usr/src/netopeer/server; \
	./configure; \
	make; \
	make install; \
	mkdir /usr/etc/netopeer; \
	mkdir /usr/etc/netopeer/cfgnetopeer; \
	cp -v config/datastore.xml /usr/etc/netopeer/cfgnetopeer/datastore.xml

# 3. build and install netopeer-cli
COPY cli /usr/src/netopeer/cli
RUN set -e -x; \
	yum install -y readline-devel.x86_64; \
	cd /usr/src/netopeer/cli; \
	ls -lah; \
	./configure; \
	make; \
	make install;



# 4. Build and install transAPI/cfgsystem
COPY transAPI/cfgsystem /usr/src/netopeer/cfgsystem
RUN set -e -x; \
	cd /usr/src/netopeer/cfgsystem; \
	./configure; \
	make; \
	make install; \
	mkdir /usr/etc/netopeer/ietf-system; \
	touch /usr/etc/netopeer/ietf-system/datastore.xml; \
	sed -i '/<hostname>/d' /usr/etc/netopeer/ietf-system/datastore.xml; \
	cp -r /usr/local/lib/python2.7/site-packages/netopeer /usr/lib/python2.7/site-packages/netopeer;


# 5. Build and install netopeer_libs through netopeer-manager
COPY netopeer_libs /usr/src/netopeer/netopeer_libs

#Create dir for datastore
RUN mkdir /home/datastore
RUN touch /usr/local/etc/netopeer/cfgnetopeer/datastore.xml
RUN mkdir /var/log/netopeer-server/

##6. Installing transapi moduels

# 6.1 build and install tpa
RUN set -e -x; \
	cd /usr/src/netopeer/netopeer_libs/tpa; \
	lnctool --model tpa.yang convert; \
	lnctool --model tpa.yang validation; \
	lnctool --model tpa.yang transapi; \
	autoreconf --force --install; \
	./configure;

COPY netopeer_libs/tpa/tpa.c /usr/src/netopeer/netopeer_libs/tpa/tpa.c
COPY netopeer_libs/tpa/Makefile /usr/src/netopeer/netopeer_libs/tpa/Makefile


RUN set -e -x; \
	cd /usr/src/netopeer/netopeer_libs/tpa; \
	make install; \
	netopeer-manager add --name tpa --model /usr/src/netopeer/netopeer_libs/tpa/tpa.yin --datastore /home/datastore/datastore-tpa.xml --transapi /usr/local/lib/tpa.so;



# 6.2 Build and install wss
RUN set -e -x; \
	cd /usr/src/netopeer/netopeer_libs/wss; \
	lnctool --model wss.yang convert; \
	lnctool --model wss.yang validation; \
	lnctool --model wss.yang transapi; \
	autoreconf --force --install; \
	./configure;


COPY netopeer_libs/wss/wss.c /usr/src/netopeer/netopeer_libs/wss/wss.c
COPY netopeer_libs/wss/Makefile /usr/src/netopeer/netopeer_libs/wss/Makefile

RUN set -e -x; \
        cd /usr/src/netopeer/netopeer_libs/wss; \
	make install; \
	netopeer-manager add --name wss --model /usr/src/netopeer/netopeer_libs/wss/wss.yin --datastore /home/datastore/datastore-wss.xml --transapi /usr/local/lib/wss.so;


# 6.3 Build and install dummy
RUN set -e -x; \
        cd /usr/src/netopeer/netopeer_libs/dummy; \
        lnctool --model dummy.yang convert; \
        lnctool --model dummy.yang validation; \
        lnctool --model dummy.yang transapi; \
        autoreconf --force --install; \
        ./configure;


COPY netopeer_libs/dummy/dummy.c /usr/src/netopeer/netopeer_libs/dummy/dummy.c
COPY netopeer_libs/dummy/Makefile /usr/src/netopeer/netopeer_libs/dummy/Makefile

RUN set -e -x; \
        cd /usr/src/netopeer/netopeer_libs/dummy; \
        make install; \
        netopeer-manager add --name dummy --model /usr/src/netopeer/netopeer_libs/dummy/dummy.yin --datastore /home/datastore/datastore-dummy.xml --transapi /usr/local/lib/dummy.so;




# 6.4 Build and install sbvt
RUN set -e -x; \
        cd /usr/src/netopeer/netopeer_libs/sbvt; \
        lnctool --model sliceable-bvt.yang convert; \
        lnctool --model sliceable-bvt.yang validation; \
        lnctool --model sliceable-bvt.yang transapi; \
        autoreconf --force --install; \
        ./configure;


COPY netopeer_libs/sbvt/sliceable-bvt.c /usr/src/netopeer/netopeer_libs/sbvt/sliceable-bvt.c
COPY netopeer_libs/sbvt/Makefile /usr/src/netopeer/netopeer_libs/sbvt/Makefile

RUN set -e -x; \
        cd /usr/src/netopeer/netopeer_libs/sbvt; \
        make install; \
        netopeer-manager add --name sbvt --model /usr/src/netopeer/netopeer_libs/sbvt/sliceable-bvt.yin --datastore /home/datastore/datastore-sbvt.xml --transapi /usr/local/lib/sliceable-bvt.so;




# 7 Setting up the startup config reader and the socket server
COPY startup_config_diff /usr/src/netopeer/startup_config_diff
RUN mkdir /home/cfg_files/
RUN touch /home/cfg_files/conf-tpa.txt
RUN touch /home/cfg_files/conf-wss.txt
RUN touch /home/cfg_files/conf-dummy.txt
RUN touch /home/cfg_files/conf-sbvt.txt

##Installing lib for socket server script
RUN pip3 install xmltodict
COPY socket_server/socket_server.py /usr/src/netopeer/socket_server.py
COPY inv_lookup_table.json /usr/src/netopeer/inv_lookup_table.json
RUN rm /usr/src/netopeer/startup_config_diff/config_docker.json
#COPY wrapper.sh /usr/src/netopeer/wrapper.sh
#COPY init_socket_server.sh /usr/local/bin/init_socket_server.sh
RUN echo "root:root" | chpasswd

CMD ["netopeer-server", "-v", "7"]
#CMD ["./usr/src/netopeer/wrapper.sh"]

# expose ports
EXPOSE 830
