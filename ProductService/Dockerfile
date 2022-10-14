FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} productservice.jar

ENTRYPOINT [ "java", "-jar" ,"/productservice.jar" ]

RUN echo ${JAR_FILE}

EXPOSE 9296