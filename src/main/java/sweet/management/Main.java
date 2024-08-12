package sweet.management;

import java.util.logging.*;

public class Main {
    static Logger logger;

    static {
        logger = Logger.getLogger(ColoredFormatter.class.getName());
        // Create a ConsoleHandler with the custom formatter
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new ColoredFormatter());
        // Add the handler to the logger
        logger.setUseParentHandlers(false); // Disable default console handler
        logger.addHandler(handler);
    }
    
    public static void main(String[] args) {


        // Log messages
        logger.info("This is an info message.");
        logger.warning("This is an info message.");
    }





    public static class ColoredFormatter extends Formatter {
        // ANSI escape codes for colors
        public static final String RESET = "\u001B[0m";
        public static final String BRIGHT_WHITE = "\u001B[97m";  // Bright white color
        public static final String RED = "\u001B[31m";  // Red color for warnings

        @Override
        public String format(LogRecord logRecord) {
            String color;
            // Apply color based on log level
            if (logRecord.getLevel().intValue() >= Level.WARNING.intValue()) {
                color = RED;  // Use red for WARNING and SEVERE
            } else {
                color = BRIGHT_WHITE;  // Use bright white for other levels
            }
            return color // Set the appropriate color
                    + formatMessage(logRecord) // Log message
                    + RESET // Reset color
                    + System.lineSeparator(); // New line
        }
    }
}