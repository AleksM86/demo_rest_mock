FROM amazoncorretto:17.0.3-alpine as corretto-jdk
ADD /target/newMock-0.0.1-SNAPSHOT.jar newmock.jar
ENTRYPOINT ["java","-jar","newmock.jar"]