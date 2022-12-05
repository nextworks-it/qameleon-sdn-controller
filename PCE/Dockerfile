FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/pce
COPY pce/target/pce-0.0.1.jar /opt/pce
WORKDIR /opt/pce
CMD ["java","-jar","pce-0.0.1.jar"]
