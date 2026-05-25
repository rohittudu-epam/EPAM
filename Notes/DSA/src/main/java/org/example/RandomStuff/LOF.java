package org.example.RandomStuff;

import java.util.ArrayList;
import java.util.List;

public class LOF {

    void main() {
        String[] arr = {"logintest - PASS", "carttest - FAIL", "checkouttest - FAIL", "searchtest - PASS"};
        List<String> lst = new ArrayList<>();

        for (String  s: arr){
            String[] strings = s.split(" - ");
            if (strings[1].equalsIgnoreCase("fail")) {
                lst.add(strings[0]);
            }
        }

        System.out.println(lst);
        System.out.println(String.format("Number of Failed TestCases: %d", lst.size()));
//        return count;
    }
}
