package com.gmail.psyh2409.task2.comparators;


import com.gmail.psyh2409.task2.Human;

import java.util.Comparator;

public class SurnameComparator implements Comparator<Human> {

    @Override
    public int compare(Human o1, Human o2) {
        if (o1 == null & o2 != null) return -1;
        if (o1 == null & o2 == null) return 0;
        if (o1 != null & o2 == null) return 1;

        if (o1.getSurname().compareToIgnoreCase(o2.getSurname()) > 0) return 1;
        if (o1.getSurname().compareToIgnoreCase(o2.getSurname()) == 0) return 0;
        if (o1.getSurname().compareToIgnoreCase(o2.getSurname()) < 0) return -1;

        return 0;
    }
}

