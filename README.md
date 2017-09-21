# coap-iot-server

This project is an example how you can implement a CoAP server for collecting data from IoT devices.
Common tasks when working with iot devices are:
  - receive a packet with some data, then parse and put it in the DB.
  - configure the device
  - update firmware on-the-air

Used [californium](https://github.com/eclipse/californium)

How to runing on Ubuntu.

1) Install Java

- setup JAVA_HOME

- setup default paths for Derby

2) Clone this repo

- fetch maven dependencies

- building artifacts

3) Go to in ${ROOT}/scripts/derby/

- run the derby-server
>$ sudo sh start-db-server-derby.sh

- create db (will be created in /home/root)
>$ sudo sh create-db-derby.sh

- run app
>$ java coap-wm-server.jar
