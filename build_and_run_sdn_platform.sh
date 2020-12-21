##This script builds and execute the Qameleon SDN Platform from the scratch.

cd qam_sdn_platform/common
mvn clean install
cd ../topology_app/api/
mvn clean install -Dcheckstyle.skip -DskipTests
cd ../../netconf-drivers/
mvn clean install -Dcheckstyle.skip -DskipTests
cd ../provisioning_plugin/
mvn clean install -Dcheckstyle.skip -DskipTests
cd ../provisioning_app/
mvn clean install -Dcheckstyle.skip -DskipTests
cd ../topology_plugin/
mvn clean install -Dcheckstyle.skip -DskipTests
cd ../topology_app/
mvn clean install -Dcheckstyle.skip -DskipTests
cd ..
cp provisioning_plugin/target/provisioning-plugin-0.0.1.jar topology_app/karaf/target/assembly/deploy/
cp provisioning_app/target/provisioning-app-0.1.0.jar topology_app/karaf/target/assembly/deploy/ 
topology_app/karaf/target/assembly/bin/karaf

