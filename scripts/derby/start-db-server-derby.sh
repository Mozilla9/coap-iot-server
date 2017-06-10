#
# @project: coap-iot-server
# @created: 05.06.17
# @author:  Serge Maslyakov
#


#!/usr/bin/env bash


echo "--- Run derby-sql server"

java -Dderby.system.home=/home/root/coap-iot-db -jar "${JAVA_HOME}/db/lib/derbynet.jar" start -h localhost -p 1527 &

echo "--- Server running"