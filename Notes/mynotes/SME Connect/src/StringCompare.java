public class StringCompare {

    void main(){
        String s1 = new String("Hello");
        String s2 = s1.intern();
        String s3 = "Hello";


        System.out.println(s1 == s2); // -> false
        System.out.println(s2 == s3); // -> true
//        System.out.println(s1.hashCode());
//        System.out.println(s2.hashCode());
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));
        System.out.println(System.identityHashCode(s3));
    }

//    void main(){
//        int a = 1;
//        int b = a++ + a++ + ++a;
//
//        /**
//         * a++ -> out(1) -> val(2)
//         * a++ -> out(2) -> val(3)
//         * a++ -> out(4) -> val(4)
//         * -> out 7
//         */
//        System.out.println(b);
//    }
//    new String object

}


//public class StringCompare {
//
//    public static int i = 0;
//    static {
//        i++;
//    }
//
//    void main(){
//        System.out.println(i);
//    }
//}