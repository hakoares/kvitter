# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8090 available to the world outside this container
EXPOSE 9000

# The application's jar file
ARG JAR_FILE=target/upload-1.0.war

# Add the application's jar to the container
ADD ${JAR_FILE} upload-1.0.war

# Run the jar file
ENTRYPOINT ["java","-jar","/upload-1.0.war"]