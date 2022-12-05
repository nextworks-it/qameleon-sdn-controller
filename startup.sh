#!/bin/bash
nohup python3 /app-wrapper/openroadm-netconf-plugin/netconf_plugin_openroadm.py > /app-wrapper/openroadm-netconf-plugin/logs.txt 2>&1 &
`bash /app-wrapper/karaf/target/assembly/bin/start`
while [ ! -f /app-wrapper/karaf/target/assembly/data/log/karaf.log ]; do sleep 1; done
`tail -f /app-wrapper/karaf/target/assembly/data/log/karaf.log > /proc/1/fd/1`
