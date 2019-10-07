#!/bin/bash
sudo mvn -f /home/ec2-user/ package
java -jar /home/ec2-user/target/mivi-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null &
exit 0
