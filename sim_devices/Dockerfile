FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/netconf-testtool
COPY netconf-testtool-1.7.2-executable.jar /opt/netconf-testtool
RUN mkdir /opt/netconf-testtool/simDev
COPY /simDev /opt/netconf-testtool/simDev
WORKDIR /opt/netconf-testtool
CMD ["java","-jar","netconf-testtool-1.7.2-executable.jar","--schemas-dir","simDev/","--device-count","8","--debug","true"]
