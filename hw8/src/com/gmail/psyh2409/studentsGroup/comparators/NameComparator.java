package com.gmail.psyh2409.studentsGroup.comparators;

import com.gmail.psyh2409.studentsGroup.Human;

import java.util.Comparator;

public class NameComparator implements Comparator<Human> {

    @Override
    public int compare(Human o1, Human o2) {
        if (o1 == null & o2 != null) return -1;
        if (o1 == null & o2 == null) return 0;
        if (o1 != null & o2 == null) return 1;

        if (o1.getName().compareToIgnoreCase(o2.getName()) > 0) return 1;
        if (o1.getName().compareToIgnoreCase(o2.getName()) == 0) return 0;
        if (o1.getName().compareToIgnoreCase(o2.getName()) < 0) return -1;

        return 0;
    }
}
