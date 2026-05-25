import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class incrementOp {
    static String demo(){
        return "Hello";
    }

    public static void main(String[] args){

        String s1 = new String("Hello");
        String s2 = new String("Hello");

        System.out.println(s1 == s2);
        System.out.println(s1 == demo());

//
//        String s1 = "Hello"; // String literal stored in SCP
//        String s2 = new String("Hello"); // new object stored in heap
//        String s3 = new String("Hello"); // new object created and stored in heap
//
//        System.out.println();

//        Integer i5_2 = new Integer(5);
//        Integer i5 = 5;
//        Integer i5_3 = new Integer(5);
//
//        System.out.println(System.identityHashCode(i5_3));
//        System.out.println(System.identityHashCode(i5_2));
//        System.out.println(System.identityHashCode(i5));

//        System.out.println(System.identityHashCode(i5));
//        System.out.println(System.identityHashCode(i5_2));
//        System.out.println(System.identityHashCode(i5_3));

//        String s1 = new String("Hello");
//        String s2 = s1.intern(); // -> returns SCP address of the value
//        String s3 = "Hello";
//        String s4 = new String("Hello");
//        String s5 = new String("Hello");
//
//
//        System.out.println(System.identityHashCode(s5));
//        System.out.println(System.identityHashCode(s1));
//        System.out.println(System.identityHashCode(s3));
//        System.out.println(System.identityHashCode(s2));
//        System.out.println(System.identityHashCode(s4));
//        System.out.println(s2.hashCode());
//        final int i = 0;
//        System.out.println(i++);
//        System.out.println(i);
//        System.out.println(++i);
//        System.out.println(i);

//        ArrayList<String> list = new ArrayList<>(List.of("Bharath", "Bob", "John", "Jarvis", "Bharath"));

//        Map<String, Integer> mp = list.stream().filter(s -> s.startsWith("B")).collect(
//                Collectors.toMap(c -> c, c -> 1, Integer::sum)
//        );


//        System.out.println(mp);
    }
}
