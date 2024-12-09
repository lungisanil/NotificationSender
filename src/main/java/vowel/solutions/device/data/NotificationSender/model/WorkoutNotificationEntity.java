package vowel.solutions.device.data.NotificationSender.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WORKOUT_NOTIFICATION")
public class WorkoutNotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NOTIFICATION_ID", updatable = false, nullable = false)
    private Long notificationId;

    @Column(name = "RECEIVER", updatable = false, nullable = false)
    private String receiver;

    @Column(name = "CONTENT", updatable = false, nullable = false)
    private String content;

    @Column(name = "DATE_SCHEDULED", updatable = false, nullable = false)
    private String dateScheduled;

    public Long getNotificationId() {
        return notificationId;
    }

    public WorkoutNotificationEntity setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public WorkoutNotificationEntity setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String getContent() {
        return content;
    }

    public WorkoutNotificationEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDateScheduled() {
        return dateScheduled;
    }

    public WorkoutNotificationEntity setDateScheduled(String dateScheduled) {
        this.dateScheduled = dateScheduled;
        return this;
    }

    @Override
    public String toString() {
        return "WorkoutNotificationEntity{" +
                "notificationId=" + notificationId +
                ", receiver=" + receiver +
                ", content=" + content +
                ", dateScheduled='" + dateScheduled + '\'' +
                '}';
    }
}
