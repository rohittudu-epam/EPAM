package com.epam.campus.selenium.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Demonstrates Log4j2 logging functionality.
 * Shows different log levels and exception logging.
 * 
 * @author EPAM Campus
 * @version 1.0
 */
public class Log4jLogger {

    private static final Logger logger = LogManager.getLogger(Log4jLogger.class);

    /**
     * Demonstrates various logging levels with Log4j2.
     */
    @Test
    public void testLogToConsole() {
        logger.trace("This is a TRACE level message - most detailed");
        logger.debug("This is a DEBUG level message - for debugging");
        logger.info("This is an INFO level message - general information");
        logger.warn("This is a WARN level message - potential issues");
        logger.error("This is an ERROR level message - errors occurred");
        
        // Demonstrate exception logging
        Exception testException = new RuntimeException("This is a test exception!");
        logger.error("Exception occurred during test", testException);
        
        logger.info("Logging test completed successfully");
    }
}