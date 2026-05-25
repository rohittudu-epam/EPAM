public class MethodReferenceExample {
    // public static void main(String[] args) {
    // Greeting greet = System.out::println;
    // greet.sayHello("Hello using method reference!");
    // }

    public static void main(String[] args) {

        // Method Reference
        Greeting greet = System.out::println;
        greet.sayHello("Hello using method reference!");

        // Lambda Expressions
        Greeting greet2 = n -> System.out.println(n);
        greet2.sayHello("Hey Guys!");
    }
}

@FunctionalInterface
interface Greeting {
    void sayHello(String message);
}