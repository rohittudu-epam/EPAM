package com.epam.campus;

public class UnderAgeException extends Exception {
    
    /**
     * 
     * @param message The Exception message
     */
    public UnderAgeException(String message){
        super(message);
    }

    public UnderAgeException(){
        super("User is under 18 year old!");
    }
}
