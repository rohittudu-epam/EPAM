package com.epam.campus;

import java.util.regex.*;

// SMSNotification Class extending Notification Abstract class
public class SMSNotification extends Notification {
    // SMSNotification Constructor
    public SMSNotification(String recipient) {
        super(validate(recipient));
    }

    // Overriding sendNotification Method
    @Override
    public String sendNotification() {
        return "SMS Notification sent to " + recipient + ": You've received a SMS notification.";
    }

    // recipient Validation for SMSNotification
    public static String validate(String recipient) {
        if (!Validator.matches(ValidationPatterns.SMS, recipient)) {
            throw new IllegalArgumentException("Invalid Phone Number");
        }

        return recipient;
    }   
}
