FROM openjdk:11
ENV JAVA_OPTS=""
ARG JAR_FILE
ADD ${JAR_FILE} /home/registration-service.jar
WORKDIR /home/
CMD java -jar $JAVA_OPTS registration-service.jar
