import java.util.function.Predicate;

public class CustomPredicate implements Predicate<Integer> {
    private int min;
    private int max;

    public CustomPredicate(int min, int max){
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean test(Integer x){
        return x >= min && x <= max;
    }
}
