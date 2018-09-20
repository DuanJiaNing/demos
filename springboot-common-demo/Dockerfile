FROM frolvlad/alpine-oraclejdk8:slim

COPY target/springboot-common-demo-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]