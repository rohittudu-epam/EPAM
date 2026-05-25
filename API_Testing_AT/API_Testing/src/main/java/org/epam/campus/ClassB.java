package org.epam.campus;

class A {
    void show(Object o){
        System.out.println("Object");
    }
}

public class ClassB extends A {
    void show(String s){
        System.out.println("String");
    }

    public static void main(String[] args){
        A a = new ClassB();
        a.show("Hello ");
    }
}
