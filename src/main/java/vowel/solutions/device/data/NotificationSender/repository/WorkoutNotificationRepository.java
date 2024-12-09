package vowel.solutions.device.data.NotificationSender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vowel.solutions.device.data.NotificationSender.model.WorkoutNotificationEntity;

@Repository
public interface WorkoutNotificationRepository extends JpaRepository<WorkoutNotificationEntity, Long> {
}
