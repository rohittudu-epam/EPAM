public class Test {
    public static void main(String[] args){
        String name = getName(1);
        System.out.println(name.toUpperCase());

    }

    private static String getName(int id){
        // fetching from db
        if (id < 5){
            return "Ryan";
        }

        
        return null;
    }
}
