public class Demo {
    public static void main(String[] args) {
        int num = 10;           // ✅ Stack
        String name = "John";   // Reference in Stack, Object in Heap

        calculate(num);         // Method call pushed to Stack
    }

    static int calculate(int x) {  // Parameter 'x' in Stack
        int result = x * 2;// Local variable in Stack
        int y = 100;
        int j = 99;
        return result;             // Method popped from Stack
    }
}