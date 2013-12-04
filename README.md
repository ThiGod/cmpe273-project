cmpe273-project
===============

This is the shared repository for CMPE 273 project. 

Kids On Track Service
$ mvn clean package

$ java -jar target/kidsontrack.jar server config/dev_config.yml

How to run this Java process forever
$ nohup ./bin/dev.sh 0<&- &> /tmp/kidsontrackapp.log &

Service endpoint: http://localhost:9000/kidsontrack/v1

Admin: http://localhost:9001/
