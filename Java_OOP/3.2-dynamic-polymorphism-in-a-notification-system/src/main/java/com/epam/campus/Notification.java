package com.epam.campus;

// Notification abstract class using Notifier Interface
public abstract class Notification implements Notifier {
    protected String recipient;

    // Notification Constructor
    public Notification(String recipient) {
        // Recipient should not be empty or null
        if (recipient == null || recipient.trim().isEmpty()) {
            throw new IllegalArgumentException("recipient must not be null or empty");
        }

        this.recipient = recipient;
    }

    // Getter for Recipient
    public String getRecipient() {
        return recipient;
    }

    // Abstract method sendNotification
    @Override
    public abstract String sendNotification();
}
