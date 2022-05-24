FROM openjdk:11
MAINTAINER giovanni <giovanni.dintrono>
EXPOSE 8080 8082
COPY ./build/libs/com.example.ktor-servertest-0.0.1-all.jar /usr/app/
##ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "com.example.ktor-servertest-0.0.1-all.jar"]