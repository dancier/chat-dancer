
FROM openjdk:17-slim

ADD ./target/chat-dancer.jar /chat-dancer.jar
CMD ["java",  "-jar", "/chat-dancer.jar"]
EXPOSE 8080