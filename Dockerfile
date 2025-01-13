
FROM amazoncorretto:17-alpine-jdk
COPY target/NotificationSender-1.0.0-SNAPSHOT.war NotificationSender.war
ENTRYPOINT ["java", "-jar", "/NotificationSender.war"]

#docker build -t notification-sender-image .