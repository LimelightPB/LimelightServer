# Limelight
## About
A Spring Boot/Spring Content application to receive and store images over REST. 

Designed to be used in conjunction with 
[LimelightPlugin](https://github.com/LimelightPB/LimelightPlugin).

# Usage
## Compilation 

No WAR or JAR has been compiled and released yet, so the project must be compiled from source.

## Execution
LimelightServer is written for, and tested with, Java 17 LTS. The application is 
written using Spring Boot and thus is a self-contained JAR file that embeds 
Apache Tomcat and the WAR to be run on it.

Once running, the project will set up a web server on port 8080 by default.

## Commands
Shell commands can be run from the terminal once the application has fully 
initialised.

| Command     | Description                                         |
|-------------|-----------------------------------------------------|
| help        | Lists all available commands                        |       
| clear       | Clear the terminal                                  |
| image-stats | Print stats regarding images stored in the database |

## REST endpoints
TBA

## Authentication
TBA