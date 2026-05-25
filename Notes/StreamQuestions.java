import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Arrays;

class Employee {
    private final String name;
    private final String department;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', department='%s'}", name, department);
    }
}

public class StreamQuestions {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "HR"),
            new Employee("Bob", "Finance"),
            new Employee("Charlie", "IT"),
            new Employee("Diana", "Marketing"),
            new Employee("Ethan", "IT")
        );

        Map<String, Long> departmentCounts = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        System.out.println(departmentCounts);
    }
}
    
    // Solution 1.
    // List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5));
    // Comparator<Integer> LargestSort = new sortedToLargest();
    // Optional<Integer> secondLargest =
    // nums.stream().sorted(LargestSort).skip(1).findFirst();
    // int value = (secondLargest.isPresent()) ? secondLargest.get() : -1;
    // System.out.println(value);

    // Solution 2.
    // List<String> strs = new ArrayList<>(List.of("One", "Ghost", "Ryan",
    // "Terminal", "Laptops", "Transformers"));
    // Optional<String> longestString = strs.stream().sorted((a, b) ->
    // Integer.compare(b.length(), a.length())).findFirst();
    // System.out.println(String.format("Longest String: %s",
    // longestString.isPresent() ? longestString.get(): -1));

    // Solution 3.
    // String word = "Mississipi";
    // String reducedWord =
    // String removedDuplicate = word.chars()
    // .mapToObj(c -> (char) c)
    // .distinct()
    // .map(String::valueOf)
    // .collect(Collectors.joining());
    //
    // System.out.println(String.format("Removed Duplicates: %s",
    // removedDuplicate));

    // Solution 4. Count the frequency of each character in a string using streams.
    // String word = "Mississipi";
    // Map<Character, Integer> characterCountMap = word.chars()
    // .mapToObj(c -> (char) c)
    // .collect(Collectors.toMap(
    // c -> c,
    // c -> 1,
    // Integer::sum
    // ));
    // System.out.println(characterCountMap);

    // Solution 5.
    // List<String> strings = List.of(
    // "madam",
    // "racecar",
    // "level",
    // "radar",
    // "civic",
    // "refer",
    // "kayak",
    // "noon",
    // "mom",
    // "dad"
    // );
    //
    // List<String> pallindromeStrings =
    // strings.stream().map(StringBuilder::new).filter((s) ->
    // s.equals(s.reverse())).map(s -> s.toString()).collect(Collectors.toList());
    // List<String> pallindromeStrings = strings.stream().filter(
    // s -> IntStream.range(0, s.length() / 2).allMatch(i -> s.charAt(i) ==
    // s.charAt(s.length() - i - 1)))
    // .collect(Collectors.toList());
    // System.out.println(pallindromeStrings);

    // Solution 6.