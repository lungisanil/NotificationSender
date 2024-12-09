package vowel.solutions.device.data.NotificationSender.service;

public interface EmailSender {
    void sendEmail(String emailSender, String username, String emailSubject, String message);
}
