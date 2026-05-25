
public class S1 {
    void main(){

//        String s_1 = "Hello";
//        String s_2 = "Hello";
//
//        System.out.println(System.identityHashCode(s_1));
//        System.out.println(System.identityHashCode(s_2));


        String s1 = new String("Hello");
        String s2 = new String("Hello");

        System.out.println(s1.intern() == s2.intern());

//        System.out.println(System.identityHashCode(s1));
//        System.out.println(System.identityHashCode(s2));
//
//       String s3 = "Hello";
//       System.out.println(System.identityHashCode(s3));

//        Find the first non-repeated character in a string.
//        String s = "Mississipi";
//        Optional<Character> c = s.chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
//                .entrySet().stream()
//                .filter(e -> e.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .findFirst();
//
//        if (c.isPresent()){
//            System.out.println(c);
//        }
    }
}
