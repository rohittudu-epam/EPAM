import java.util.function.Consumer;

public class ConsumerAndThen {
    public static void main(String[] args) {
        Consumer<String> first = s -> System.out.println("First: " + s);
        Consumer<String> second = s -> System.out.println("Second: " + s.toUpperCase());

        // Chaining consumers
        Consumer<String> combined = first.andThen(second);

        combined.accept("hello");

    }
}
