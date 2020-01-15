FROM openjdk:8-jdk-alpine
ADD target/Core_Role-*.jar corerole.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar", "corerole.jar"]