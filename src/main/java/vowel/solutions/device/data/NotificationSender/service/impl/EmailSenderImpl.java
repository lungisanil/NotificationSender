package vowel.solutions.device.data.NotificationSender.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import vowel.solutions.device.data.NotificationSender.exception.SendMailServiceException;
import vowel.solutions.device.data.NotificationSender.service.EmailSender;

@Service
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMailMessage;

    @Autowired
    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        this.simpleMailMessage = new SimpleMailMessage();
    }

    @Override
    public void sendEmail(String emailSender, String emailReceiver, String emailSubject, String message) {
        this.simpleMailMessage.setFrom(emailSender);
        this.simpleMailMessage.setTo(emailReceiver);
        this.simpleMailMessage.setSubject(emailSubject);
        this.simpleMailMessage.setText(message);

        try {
            this.javaMailSender.send(this.simpleMailMessage);
        } catch (Exception e) {
            throw new SendMailServiceException("Sending the email failed");
        }
    }
}
