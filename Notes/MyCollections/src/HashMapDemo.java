import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args){
        HashMap map = new HashMap();

//        Adding Values
        map.put("Ryan", "Parker");
        map.put(10, "Jackson");

        System.out.println(map);

//        To remove
//        map.remove(10);
        System.out.println(map);

//        Get all the keys
        System.out.println(map.keySet());
        System.out.println(map.values());

//        keys along with values
        System.out.println(map.entrySet());
    }

//    Accessing using iterator
    
}
