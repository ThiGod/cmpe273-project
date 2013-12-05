mpe273-project
===============

This is the shared repository for CMPE 273 project. This project uses DropWizard, MongoDB and AWS SNS.

Notes: Before you run this progream, you need to update dev_config.yml with your own Amazon Web Service (AWS) account security accessId and secret. Because these information are personal specific security information, they are not uploaded into GitHub.

You can follow the following guide to get your own AWS account id and secret so that you can update the configuration files.
http://www.cloudberrylab.com/blog/how-to-find-your-aws-access-key-id-and-secret-access-key-and-register-with-cloudberry-s3-explorer/

Kids On Track Service
$ mvn clean package

$ java -jar target/kidsontrack.jar server config/dev_config.yml

How to run this Java process forever
$ nohup ./bin/dev.sh 0<&- &> /tmp/kidsontrackapp.log &

Service endpoint: http://localhost:9000/kidsontrack/v1

Admin: http://localhost:9001/

How to start mongod as a Daemon
mongod  --fork --logpath /home/ubuntu/log/mongodb.log
