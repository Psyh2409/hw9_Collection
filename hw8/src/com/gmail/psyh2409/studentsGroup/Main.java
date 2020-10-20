package com.gmail.psyh2409.studentsGroup;

import com.gmail.psyh2409.studentsGroup.exception.RedundantStudentException;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(Sex.male, "ewfv", "First", 100, 1);
        Student student2 = new Student(Sex.valueOf("female"), "trfhmt", "Second", 100500, 2);
        Student student3 = new Student(Sex.female, "sets", "Third", 16, 3);
        Student student4 = new Student(Sex.male, "fgurmf", "Fourth", 18, 4);
        Student student5 = new Student(Sex.male, "wsghjlo", "Fifth", 27, 5);
        Student student6 = new Student(Sex.male, "hjg", "Sixth", 15, 6);
        Student student7 = new Student(Sex.female, "yukht", "Seventh", 19, 7);
        Student student8 = new Student(Sex.male, "ogfdsedtgr", "Eighth", 19, 8);
        Student student9 = new Student(Sex.male, "edr", "Ninth", 42, 9);
        Student student10 = new Student(Sex.female, "uoi", "Tenth", 60, 10);
        Student student11 = new Student(Sex.male, "bmm", "Eleventh", 20, 11);

        Group group = new Group(new Student[]{student1, null, student3, student4, student5,
                student6, student7, student8, student9, student10});

        System.out.println("Students' group.");
        for (Student student : group.getStudents()) {
            System.out.println(student);
        }

        group.removeStudent(9);
        System.out.println();
        System.out.println("group.removeStudent(9);");
        for (Student student : group.getStudents()) {
            System.out.println(student);
        }

        System.out.println();
        System.out.println("group.findStudent(\"Eighth\")");
        String string = "Student doesn't exist.";
        Student student = group.findStudent("Eighth");
        System.out.println(student == null ? string : student);
        System.out.println("group.findStudent(\"Ninth\")");
        student = group.findStudent("Ninth");
        System.out.println(student == null ? string : student);


        System.out.println();
        System.out.println("group.addStudent(student11);");
        try {
            group.addStudent(student11);
        } catch (RedundantStudentException rse) {
            System.out.println(rse.getMessage());
        }

        System.out.println();
        System.out.println("group.toString();");
        System.out.println(group);

        System.out.println();
        System.out.println("group.addStudent(student9);");
        try {
            group.addStudent(student9);
        } catch (RedundantStudentException rse) {
            System.out.println(rse.getMessage());
            rse.printStackTrace();
        }

        System.out.println();
        System.out.println("group.addStudent(student2); // Must be redundant.");
        try {
            group.addStudent(student2);  // Must be redundant.
        } catch (RedundantStudentException rse) {
            System.out.println(rse.getMessage());
            rse.printStackTrace();
        }

        System.out.println();
        System.out.println("group.toString();");
        System.out.println(group);

        System.out.println();
        System.out.println("group.removeStudent(3);");
        group.removeStudent(3);
        System.out.println("group.addStudentFromKeyboard();");
//        try {
//            group.addStudentFromKeyboard();
//        } catch (RedundantStudentException e) {
//            e.printStackTrace();
//        }

        System.out.println();
        System.out.println("group.toString;");
        System.out.println(group);

        System.out.println();
        group.sortByAge();
        System.out.println("group.sortByAge();");
        for (Student stud : group.getStudents()) {
            System.out.println(stud);
        }

        System.out.println();
        group.sortByName();
        System.out.println("group.sortByName();");
        for (Student stud : group.getStudents()) {
            System.out.println(stud);
        }

        System.out.println();
        group.sortBySurname();
        System.out.println("group.sortBySurname();");
        for (Student stud : group.getStudents()) {
            System.out.println(stud);
        }

        System.out.println();
        System.out.println("group.mobilisation();");
        for (Student stud : group.mobilisation()) {
            System.out.println(stud);
        }

        System.out.println("---------------------------------------------------------------");

        group.saveToFile("sfg.txt");
        Group newGroup = new Group();
        newGroup = newGroup.readFromFile("sfg.txt");

        System.out.println();
        System.out.println("newGroup.toString;");
        System.out.println(newGroup);

    }

}
