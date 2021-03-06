## About Conference Manager Application

Conference Manager is an application, which takes care of the tasks related to the scheduling of event orders for the given timeframe.

## How to run it:
### First way(using script)
First, run the following cmd in order to make the script executable:

`chmod u+x conferenceManager.sh`

Afterwards, pass the arguments and run the conferenceManager(f.e. the following cmd):

`./conferenceManager.sh A World Without HackerNews 30min, User Interface CSS in Rails Apps 30min`

The result of above bash command can be seen in the following screenshot:
![](Screenshot-bash.png)

### Second way(using Docker)
Either, build the docker image using below command:

`docker build -t conference-manager .`

Or, pull my Docker image from Docker hub:

`docker pull khaydarovsh/conferencemanager`

Afterwards, run the created/imported docker image(f.e. the following cmd):

`docker run --rm -it conference-manager:latest A World Without HackerNews 30min, User Interface CSS in Rails Apps 30min`

The result of above Docker command can be seen in the following screenshot:
![](Screenshot-docker.png)
