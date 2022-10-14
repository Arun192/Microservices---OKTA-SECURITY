FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} paymentservice.jar

ENTRYPOINT [ "java", "-jar" ,"/paymentservice.jar" ]

RUN echo ${JAR_FILE}

EXPOSE 9296