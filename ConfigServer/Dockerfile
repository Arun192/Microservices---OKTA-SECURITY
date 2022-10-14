FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} configserver.jar

ENTRYPOINT [ "java", "-jar" ,"/configserver.jar" ]

RUN echo ${JAR_FILE}

EXPOSE 9296