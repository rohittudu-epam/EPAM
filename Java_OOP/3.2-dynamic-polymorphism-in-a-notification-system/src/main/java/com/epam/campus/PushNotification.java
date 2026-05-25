package com.epam.campus;

import java.util.regex.*;

// PushNotification Class extending Notification Abstract class
public class PushNotification extends Notification {
    // PushNotification Constructor
    public PushNotification(String recipient) {
        super(validate(recipient));
    }

    // Overriding sendNotification Method
    @Override
    public String sendNotification() {
        return "Push Notification sent to " + recipient + ": You've received a push notification.";
    }

    // recipient Validation for Push Notification
    public static String validate(String recipient) {
        if (!Validator.matches(ValidationPatterns.DEVICE_TOKEN, recipient)) {
            throw new IllegalArgumentException("Incorrect Token for Push Notification");
        }

        return recipient;
    }
}
