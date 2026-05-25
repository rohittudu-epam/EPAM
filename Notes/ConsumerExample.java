import java.util.function.Consumer;


public class ConsumerExample {
    public static void main(String[] args){
        Consumer<String> printConsumer = s -> System.out.println(s);

        printConsumer.accept("Bravo Six, Going Dark!");
    }
}
