package org.epam.campus;

class Parent {
    static int x = 10;
    static {
        System.out.print("P ");
    }
}

class Child extends Parent{
    static int y = 20;
    static {
        System.out.print("C ");
    }
}

public class Test {
    public static void main(String[] args){
        System.out.println(Child.x);
    }
}