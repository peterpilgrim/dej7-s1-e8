# Digital Java EE 7 Episode 8

This is the instruction file for the recording.

## Demonstration

The exercise follows an Agile user story:

    As a WEBSITE OWNER
    I want a RESTful Web Service 
    So that my EXCHANGE SERVICE customers can access football team and stadia information via HTTP.



The stakeholder wants the solution to fulfill the following requirements:

The solution must cater for football stadiums and record the properties.

* Stadium data
* Name
* City
* Club
* Stadium Capacity
* Competition or League Status
* Date of Creation


The solution respond to the RESTful requests and delivers a valid response. These are the compulsory API calls.

* Get a list of all stadiums in JSON format.
* Get a list of teams sorted by their stadium capacity in JSON.
* Get the details of a specific team in JSON format 
 
    
    



# Software requirements

* Java 8 (JDK 1.8.0_60 or better) - I am using JDK 8 update 121
* Gradle 3.2 or better - I am using Gradle 4.1
* Access to the Internet!


## License
See `LICENSE.txt' for the license for this source code


## Building the software

Use Gradle from the command line or a suitable IDE like JetBrains IDEA, Eclipse or NetBeans

From the command line, clean the build beforehandL

    > gradle clean 
    
Build the software from scratch:
    
    > gradle build
    
Run the unit tests

    > gradle test --info
    
    
Run the main application as a Payara Micro service 

    > gradle runMicro
    


Build a Payara Micro uber JAR with the following command

    > gradle uberJar
    

To build a Docker stage, execute the following

    > gradle dockerStage
    
    
To build a Docker image, then execute the following
    
    > gradle dockerBuild
    
    

    
## Additional Notes

Commands

    docker-machine ip 
    
    eval $(docker-machine)
    
    
    docker images
    
    docker ps
    



Building the docker side

    gradle uberJar
    cp build/libs/restful-stadia-services.jar   src/main/docker/images/payara/
    docker build -t peterpilgrim/restful-stadia-services:1.0  src/main/docker/images/payara    
         

Running the program using Docker

    docker run -p 8080:8080 -d peterpilgrim/restful-stadia-services:1.0



    docker-machine ip default
    
    
    curl http://192.168.99.100:8080/stadium/data
    

## Exercises for reader

Here are some exercises for the reader.

1. The application has no logging at all and it definitely has no metrics. Your exercise is to deliver the logging and provide metrics. 

2. The application has no unit testing at all. How do we know that the code is production worthy? You must add unit testing to code.

   


# Credits


Peter Pilgrim
Thursday 10th August 2017
Web: www.xenonique.co.uk
Twitter: peter_pilgrim (please follow me!)
