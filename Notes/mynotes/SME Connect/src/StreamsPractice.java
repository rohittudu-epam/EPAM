//import java.util.*;
//import java.util.stream.Collectors;
//
//public class StreamsPractice {
////    void main(){
////        ArrayList<Integer> list = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5));
////        Integer num = list.stream().reduce(1, (a, b) -> a * b);
////
////        System.out.println(num);
////        Collections.reverse();
//
//
//
////        Find the first non-repeated character in a string.
////        String s1 = "Mississipi";
////
////        Character sol = s1.chars().map(c -> (char) c).collect(Collectors.toMap(c -> c, c -> 1, Integer::sum).filter(entry -> entry.get() == 1).findFirst());
////        System.out.println(sol);
//
////        ArrayList<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4));
////
////        Optional<Integer> result = nums.stream().map(x -> x*x).findAny();
////
////        System.out.println(nums);
////        if (result.isPresent()){
////            System.out.println(result.get());
////        }
////
////        System.out.println();
//    }
//}
