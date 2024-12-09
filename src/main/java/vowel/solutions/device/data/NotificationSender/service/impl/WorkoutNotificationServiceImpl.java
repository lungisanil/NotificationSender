package vowel.solutions.device.data.NotificationSender.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vowel.solutions.device.data.NotificationSender.exception.InternalServerErrorException;
import vowel.solutions.device.data.NotificationSender.model.WorkoutNotificationEntity;
import vowel.solutions.device.data.NotificationSender.repository.WorkoutNotificationRepository;
import vowel.solutions.device.data.NotificationSender.service.EmailSender;
import vowel.solutions.device.data.NotificationSender.service.WorkoutNotificationService;
import vowel.solutions.device.data.device.data.event.notification.PointsNotificationRequest;

import java.time.LocalDate;

@Service
public class WorkoutNotificationServiceImpl implements WorkoutNotificationService {
    @Value("${emailDispatcherAddress}")
    private String emailDispatcherAddress;

    private final WorkoutNotificationRepository workoutNotificationRepository;
    private final EmailSender emailSenderService;

    @Autowired
    public WorkoutNotificationServiceImpl(WorkoutNotificationRepository workoutNotificationRepository, EmailSender emailSenderService) {
        this.workoutNotificationRepository = workoutNotificationRepository;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void dispatchNotification(PointsNotificationRequest notificationRequest) {
        WorkoutNotificationEntity notificationEntity = this.persistWorkoutNotificationEntity(notificationRequest);
        this.sendNotification(notificationRequest, notificationEntity);
    }

    private WorkoutNotificationEntity persistWorkoutNotificationEntity(PointsNotificationRequest notificationRequest) {
        WorkoutNotificationEntity notificationEntity;
        try {
            notificationEntity = this.workoutNotificationRepository.save(new WorkoutNotificationEntity()
                    .setReceiver(notificationRequest.getMemberEmailAddress())
                    .setDateScheduled(LocalDate.now().toString())
                    .setContent(String.format("Congratulations !!! You have been allocated %s for doing %s, your total points is now %s",
                            notificationRequest.getMemberEventPoints(),
                            notificationRequest.getMnemonicDescription(),
                            notificationRequest.getMemberTotalPoints())));
        } catch (Exception ex) {
            throw new InternalServerErrorException(String.format("Error occurred when trying to persist notification for receiver %s to the DB",
                    notificationRequest.getMemberEmailAddress()));
        }
        return notificationEntity;
    }

    private void sendNotification(PointsNotificationRequest notificationRequest, WorkoutNotificationEntity notificationEntity) {
        try {
            this.emailSenderService.sendEmail(emailDispatcherAddress,
                    notificationRequest.getMemberEmailAddress(),
                    "Keep Smashing Your Exercise Workout",
                    notificationEntity.getContent());
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex);
        }
    }
}
