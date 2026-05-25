package com.epam.campus;

import java.util.Scanner;

public class ValidateUserAge {
    
    /**
     * 
     * @param age The age to validate
     * @throws UnderAgeException if age is less than 18
     */

    public void validateAge(int age) throws UnderAgeException {
        if (age < 18){
            throw new UnderAgeException("Sorry, you must be at least 18 years old. You entered: " + age);
        }
        System.out.println("Age validation successful! You are " + age + " years old.");
    }
    
    // Prompts the user to input their age and validates it
    public void checkUserAge(){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Please enter your age: ");
            int age = scanner.nextInt();

            // validate age
            validateAge(age);
        } catch (UnderAgeException e){
            System.out.println("UnderAgeException caught: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Invalid Input! Please Enter a valid number.");
        } finally {
            scanner.close();
        }

    }
}
