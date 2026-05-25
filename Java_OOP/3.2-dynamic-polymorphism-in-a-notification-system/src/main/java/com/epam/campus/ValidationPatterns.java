package com.epam.campus;
import java.util.regex.*;

public class ValidationPatterns {
    
    private ValidationPatterns(){}

    public static final Pattern DEVICE_TOKEN = Pattern.compile("^deviceToken-[a-z0-9]{6,32}$");

    public static final Pattern EMAIL = Pattern.compile("^(.+)@(.+)$");

    public static final Pattern SMS = Pattern.compile("^\\(?([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$");
}
