package com.epam.campus;

/**
 * The {@code App} class serves as the entry point for the application.
 * <p>
 * It demonstrates the usage of the {@link ConsumerChaining} class by inserting
 * several strings (names) and then displaying them along with their lengths.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     java com.epam.campus.App
 * </pre>
 * </p>
 *
 * @author Your Name
 */
public class App {

    /**
     * The main method is the entry point of the application.
     * <p>
     * It creates an instance of {@link ConsumerChaining}, inserts several
     * sample strings, and then displays each string with its length.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ConsumerChaining cc = new ConsumerChaining();

        cc.insertString("Jack Ryan");
        cc.insertString("Captain John Price");
        cc.insertString("Johnny 'Soap' McTavish");
        cc.insertString("Aiden Pierce");
        cc.insertString("Damien Brenks");
        cc.insertString("Marcus Halloway");

        cc.displayStringsWithLength();
    }
}