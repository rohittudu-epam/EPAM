import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Bravo Six, Going Dark!";

        String result = supplier.get();
        System.out.println(result);
    }
}
