package org.example.BoundaryValueAnalysis;

// Password should be Greater than 7 characters and Less than 17 characters
public class PasswordValidator {
    public static boolean isValidPassword(String password){
        if (password == null){
            return false;
        }

        return password.length() >= 8 && password.length() <= 16;
    }
}
