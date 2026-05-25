import java.lang.reflect.*;

public class AccessFlagViewer {
    public static void main(String[] args) throws Exception {

        Class<?> clazz = Employee.class;

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║           CLASS ACCESS FLAGS VIEWER                    ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");

        // Class modifiers
        System.out.println("\n▶ CLASS: " + clazz.getName());
        System.out.println("  Modifiers: " + Modifier.toString(clazz.getModifiers()));
        System.out.println("  Hex Value: 0x" + Integer.toHexString(clazz.getModifiers()));

        // Fields
        System.out.println("\n▶ FIELDS:");
        System.out.println("  ┌─────────────────┬──────────────────┬─────────────┐");
        System.out.println("  │ Name            │ Modifier         │ Hex Value   │");
        System.out.println("  ├─────────────────┼──────────────────┼─────────────┤");

        for (Field field : clazz.getDeclaredFields()) {
            int mod = field.getModifiers();
            String modStr = Modifier.toString(mod);
            if (modStr.isEmpty()) modStr = "(default)";

            System.out.printf("  │ %-15s │ %-16s │ 0x%04x      │%n",
                    field.getName(), modStr, mod);
        }
        System.out.println("  └─────────────────┴──────────────────┴─────────────┘");

        // Methods
        System.out.println("\n▶ METHODS:");
        System.out.println("  ┌─────────────────┬──────────────────┬─────────────┐");
        System.out.println("  │ Name            │ Modifier         │ Hex Value   │");
        System.out.println("  ├─────────────────┼──────────────────┼─────────────┤");

        for (Method method : clazz.getDeclaredMethods()) {
            int mod = method.getModifiers();
            String modStr = Modifier.toString(mod);
            if (modStr.isEmpty()) modStr = "(default)";

            System.out.printf("  │ %-15s │ %-16s │ 0x%04x      │%n",
                    method.getName(), modStr, mod);
        }
        System.out.println("  └─────────────────┴──────────────────┴─────────────┘");

        // Explain flags
        System.out.println("\n▶ ACCESS FLAG VALUES:");
        System.out.println("  ┌────────────────────┬────────────┐");
        System.out.println("  │ Modifier           │ Hex Value  │");
        System.out.println("  ├────────────────────┼────────────┤");
        System.out.println("  │ PUBLIC             │ 0x0001     │");
        System.out.println("  │ PRIVATE            │ 0x0002     │");
        System.out.println("  │ PROTECTED          │ 0x0004     │");
        System.out.println("  │ STATIC             │ 0x0008     │");
        System.out.println("  │ FINAL              │ 0x0010     │");
        System.out.println("  │ SYNCHRONIZED       │ 0x0020     │");
        System.out.println("  │ VOLATILE           │ 0x0040     │");
        System.out.println("  │ TRANSIENT          │ 0x0080     │");
        System.out.println("  │ NATIVE             │ 0x0100     │");
        System.out.println("  │ ABSTRACT           │ 0x0400     │");
        System.out.println("  │ STRICT             │ 0x0800     │");
        System.out.println("  └────────────────────┴────────────┘");

        System.out.println("\n╚════════════════════════════════════════════════════════╝");
    }
}