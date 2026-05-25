package com.epam.campus;

public class App {
    public static void main(String[] args) {

        // Notification Objects
        Notification[] notifications = {
                new EmailNotification("rayman@epam.com"),
                new PushNotification("deviceToken-abc123"),
                new SMSNotification("+12879767656")
        };

        // Dynamic calling of Methods
        for (Notification N : notifications) {

            // sendNotification Method Calling
            N.sendNotification();
        }
    }
}
