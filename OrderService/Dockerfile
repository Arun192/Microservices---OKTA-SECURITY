FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} orderservice.jar

ENTRYPOINT [ "java", "-jar" ,"/orderservice.jar" ]

RUN echo ${JAR_FILE}

EXPOSE 9296