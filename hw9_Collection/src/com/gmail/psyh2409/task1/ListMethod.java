package com.gmail.psyh2409.task1;

import java.util.ArrayList;
import java.util.List;

public class ListMethod {

    public static <T> void listManipulator(T... t){
        if (t != null || t.length == 10) {
            List<T> list = new ArrayList<T>();
            for (int i = 0; i < t.length; i++) {
                list.add(t[i]);
                if (i == 0 || i == 1 || i == t.length-1) {
                    list.remove(list.size()-1);
                }
            }
            System.out.println(list);
        }
    }
}
