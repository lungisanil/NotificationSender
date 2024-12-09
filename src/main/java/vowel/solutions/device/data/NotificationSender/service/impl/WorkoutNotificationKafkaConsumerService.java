package vowel.solutions.device.data.NotificationSender.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import vowel.solutions.device.data.NotificationSender.exception.InternalServerErrorException;
import vowel.solutions.device.data.NotificationSender.service.KafkaConsumerService;
import vowel.solutions.device.data.NotificationSender.service.WorkoutNotificationService;
import vowel.solutions.device.data.device.data.event.notification.PointsNotificationRequest;

@Service
public class WorkoutNotificationKafkaConsumerService implements KafkaConsumerService<PointsNotificationRequest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkoutNotificationKafkaConsumerService.class);

    @Value("${spring.kafka.consumer.topic.name}")
    private String consumerTopic;

    private final WorkoutNotificationService workoutNotificationService;

    @Autowired
    public WorkoutNotificationKafkaConsumerService(WorkoutNotificationService workoutNotificationService) {
        this.workoutNotificationService = workoutNotificationService;
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void readMessage(PointsNotificationRequest notificationRequest) {
        LOGGER.info(String.format("Message received from " + consumerTopic + " -> %s", notificationRequest.toString()));

        try {
            this.workoutNotificationService.dispatchNotification(notificationRequest);
            LOGGER.info(String.format("Notification has successfully been sent to : %s ", notificationRequest.getMemberEmailAddress()));
        } catch (Exception ex) {
            throw new InternalServerErrorException(String.format("Error occurred when trying to send notification to : %s ", notificationRequest.getMemberEmailAddress()));
        }
    }
}
