package vowel.solutions.device.data.NotificationSender.service;

import vowel.solutions.device.data.device.data.event.notification.PointsNotificationRequest;

public interface WorkoutNotificationService {
    void dispatchNotification(PointsNotificationRequest notificationRequest);
}
