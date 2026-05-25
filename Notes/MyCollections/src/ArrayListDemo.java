import java.util.*;

public class ArrayListDemo {
    public static void main(String[] args){

        // Declaration
//        ArrayList mylist = new ArrayList(); -> correct syntax

        List myList = new ArrayList();
//        ArrayList<Integer> ints = new ArrayList<Integer>();

//        Adding data into arraylist
        myList.add(10);
        myList.add(20);
        myList.add("HeteroGeneous Data");
        myList.add("Ghost");
        myList.add(10.5);
        myList.add(99);
        myList.add("Hello");

        System.out.println(myList);

//        Size of an ArrayList
//        System.out.println(myList.size());

//        Printing Arraylist
//        System.out.println(myList);

//        To remove an element
        myList.remove(5); // -> removes element at 5th index

//        To insert an element
        myList.add(1, 10); // -> adding element 10 at index 1

//        Reading all the values

//        for (int i = 0; i < myList.size(); i++){
//            System.out.println(myList.get(i));
//        }

// Iterator method
        Iterator it = myList.iterator();
        System.out.println(it);

        List myList2 = new ArrayList();
        myList2.add(10);
        myList2.add(10.5);
        myList2.add("Hello");

        System.out.println(myList);
        myList.removeAll(myList2);
        System.out.println(myList);
    }
}
