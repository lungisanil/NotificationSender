
FROM amazoncorretto:17-alpine-jdk
COPY target/NotificationSender-1.0.0-SNAPSHOT-1.0.0 NotificationSender.war
ENTRYPOINT ["java", "-jar", "/NotificationSender.war"]

#docker build -t notification-sender-image .