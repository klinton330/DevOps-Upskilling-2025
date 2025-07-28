#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Author
LABEL "org.opencontainers.image.authors"="eazybytes.com"

#Add application jar to image
COPY target/account-service-app-0.0.1-SNAPSHOT.jar account-service-app-0.0.1-SNAPSHOT.jar 

#Execute the application
ENTRYPOINT ["java","-jar","account-service-app-0.0.1-SNAPSHOT.jar"]


#Build Images
#docker build . -t klinton/account-service-app:v1