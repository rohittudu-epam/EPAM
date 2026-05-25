import java.util.function.Predicate;

public class CustomPredicateImplementation {
    public static void main(String[] args){
        Predicate<Integer> p = new CustomPredicate(55, 99);

        System.out.println(p.test(99));
        System.out.println(p.test(100));
    }
}
