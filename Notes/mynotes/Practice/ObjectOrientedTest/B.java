package ObjectOrientedTest;

public class B extends A {
    private int a;

    public B(int a, int b) {
        super(a, b);
    }

    public void methodOfA(){
        System.out.println("This is Method of B");
    }


    public void runMethodA(){
        methodOfA();
    }
}
