package com.epam.campus;
import java.util.regex.*;

public final class Validator {
    
    private Validator(){}

    public static boolean matches(Pattern pattern, String value){
        return value != null && pattern.matcher(value).matches();
    }
}
