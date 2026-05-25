package com.epam.campus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Test
    void testValidEmails() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList("test@example.com", "user.name@domain.co", "valid.email@sub.domain.org");
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertEquals(Arrays.asList(true, true, true), results);
    }

    @Test
    void testInvalidEmails() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList("invalid-email", "user@domain", "@missingusername.com");
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertEquals(Arrays.asList(false, false, false), results);
    }

    @Test
    void testMixedEmails() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList("valid@example.com", "invalid-email", "user@domain");
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertEquals(Arrays.asList(true, false, false), results);
    }

    @Test
    void testEmptyEmail() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList("");
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertEquals(Arrays.asList(false), results);
    }

    @Test
    void testNullEmail() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList((String) null);
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertEquals(Arrays.asList(false), results);
    }

    @Test
    void testEmailsWithSpecialCharacters() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList("user+name@example.com", "user_name@domain.com", "user-name@domain.com");
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertEquals(Arrays.asList(true, true, true), results);
    }

    @Test
    void testEmailsWithInvalidDomain() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList("user@domain.c", "user@domain.toolongtld", "user@.com");
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertEquals(Arrays.asList(false, false, false), results);
    }

    @Test
    void testEmailsWithSpaces() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList("user @domain.com", " user@domain.com ", "user@ domain.com");
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertEquals(Arrays.asList(false, false, false), results);
    }

    @Test
    void testEmailsWithMultipleAtSymbols() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList("user@@domain.com", "user@domain@com", "user@domain.com@");
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertEquals(Arrays.asList(false, false, false), results);
    }

    @Test
    void testEmailsWithEmptyList() {
        EmailValidator emailValidator = new EmailValidator();
        CustomEmailValidator validator = email -> email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

        List<String> emails = Arrays.asList();
        List<Boolean> results = emailValidator.validateEmails(emails, validator);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
