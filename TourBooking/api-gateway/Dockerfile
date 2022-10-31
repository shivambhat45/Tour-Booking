FROM openjdk:11
ENV JAVA_OPTS=""
ARG JAR_FILE
ADD ${JAR_FILE} /home/api-gateway.jar
WORKDIR /home/
CMD java -jar $JAVA_OPTS api-gateway.jar
