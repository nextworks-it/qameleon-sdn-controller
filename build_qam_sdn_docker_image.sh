##This script builds the Qameleon SDN Controller from the scratch. It takes a few minutes
cd qam_sdn_platform/common/
mvn clean install -Dcheckstyle.skip -DskipTests -Dmaven.javadoc.skip=true
cd ../netconf-drivers/
mvn clean install -Dcheckstyle.skip -DskipTests -Dmaven.javadoc.skip=true
cd ../provisioning_plugin/
mvn clean install -Dcheckstyle.skip -DskipTests -Dmaven.javadoc.skip=true
cd ../provisioning_app/
mvn clean install -Dcheckstyle.skip -DskipTests -Dmaven.javadoc.skip=true
cd ../topology_plugin/
mvn clean install -Dcheckstyle.skip -DskipTests -Dmaven.javadoc.skip=true
cd ../topology_app/
mvn clean install -Dcheckstyle.skip -DskipTests -Dmaven.javadoc.skip=true -pl -api,-cli,-features

cd ..
cp provisioning_plugin/target/provisioning-plugin-0.0.1.jar topology_app/karaf/target/assembly/deploy/
cp provisioning_app/target/provisioning-app-0.1.0.jar topology_app/karaf/target/assembly/deploy/
cd ..
cp org.ops4j.pax.logging.cfg ./qam_sdn_platform/topology_app/karaf/target/assembly/etc/org.ops4j.pax.logging.cfg
sudo docker build . -t qam_sdn_controller:0.0.1
