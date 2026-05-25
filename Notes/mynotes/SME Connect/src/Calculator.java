public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(5, 3));
        System.out.println(calc.add(5.0, 3.0));
        System.out.println(calc.add(5, 3.0));
    }
}