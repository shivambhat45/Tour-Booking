FROM openjdk:11
ENV JAVA_OPTS=""
ARG JAR_FILE
ADD ${JAR_FILE} /home/tour-service.jar
WORKDIR /home/
CMD java -jar $JAVA_OPTS tour-service.jar
