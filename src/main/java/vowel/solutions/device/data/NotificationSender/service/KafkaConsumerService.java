package vowel.solutions.device.data.NotificationSender.service;

public interface KafkaConsumerService<M> {
    void readMessage(M message);
}
