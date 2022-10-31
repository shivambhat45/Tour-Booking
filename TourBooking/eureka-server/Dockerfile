FROM openjdk:11
ENV JAVA_OPTS=""
ARG JAR_FILE
ADD ${JAR_FILE} /home/eureka-server.jar
WORKDIR /home/
CMD java -jar $JAVA_OPTS eureka-server.jar
