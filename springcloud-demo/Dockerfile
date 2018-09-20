FROM frolvlad/alpine-oraclejdk8:slim

COPY target/springcloud-demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]