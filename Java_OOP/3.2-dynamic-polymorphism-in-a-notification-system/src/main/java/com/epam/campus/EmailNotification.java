package com.epam.campus;

// EmailNotification Class Extending Notification Abstract Class
public class EmailNotification extends Notification {
    // EmailNotification Constructor
    public EmailNotification(String recipient) {
        super(validate(recipient));
    }

    // Overriding sendNotification Method
    @Override
    public String sendNotification() {
        return "EMAIL Notification sent to " + recipient + ": You've received an EMAIL notification.";
    }

    // validating the recipient
    private static String validate(String recipient) {
        if (!Validator.matches(ValidationPatterns.EMAIL, recipient)) {
            throw new IllegalArgumentException("Email is not valid");
        }

        return recipient;
    }

}