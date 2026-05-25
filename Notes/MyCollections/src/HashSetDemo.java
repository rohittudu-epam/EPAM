import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args){
//        Declaration
        Set mySet = new HashSet();
//        HashSet newHashSet = new HashSet(); // -> heterogeneous declaration
//        HashSet<String> newHashSet = new HashSet<String>();

//        Adding Elements
        mySet.add(10);
        mySet.add(20);
        mySet.add(30);
        mySet.add(40);
        mySet.add(50);
        mySet.add("Hello");
        mySet.add("Ghost");
        mySet.add("Marcus");
//        System.out.println(mySet);

//        removing values
//        mySet.remove(value);
        mySet.remove(50);
//        System.out.println(mySet);

//        Size of a HashSet
        System.out.println(mySet.size());

//        Insertion is not possible
//        Since no order is maintained, the insertion operation is not possible

//        Access Specific Element
//        No Indexing, hence get operation is not possible
//        No Direct Access

//        Convert HashSet to ArrayList
//        ArrayList al = new ArrayList(mySet);
//        System.out.println(al);

//        Reading all the elements
//        Normal For Loop is not possible since there's no indexing available
//        Only Enhanced For Loop can be used
//        for (Object e: mySet){
//            System.out.println(e);
//        }

        Iterator it = mySet.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

}
