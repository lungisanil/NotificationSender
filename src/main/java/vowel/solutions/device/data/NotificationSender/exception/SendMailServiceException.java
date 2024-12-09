package vowel.solutions.device.data.NotificationSender.exception;

/**
 * Exception for when the app fails to send the email
 **/
public class SendMailServiceException extends AbstractServiceException {

    public SendMailServiceException(final String message){
        super(message);
    }

}
