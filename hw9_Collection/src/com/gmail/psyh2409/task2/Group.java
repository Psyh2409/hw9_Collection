package com.gmail.psyh2409.task2;

import com.gmail.psyh2409.task2.comparators.AgeComparator;
import com.gmail.psyh2409.task2.comparators.NameComparator;
import com.gmail.psyh2409.task2.comparators.SurnameComparator;
import com.gmail.psyh2409.task2.exeption.RedundantStudentException;

import java.io.*;
import java.util.*;

public class Group implements MilitaryCommissar, Serializable {
    private static final long serialVersionUID = 1L;
    private List<Student> students;

    public Group() {
        super();
        this.students = new ArrayList<>();
    }

    public Group(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }

    @Override
    public String toString() {
        sortBySurname();
        StringBuilder sortedBySurname = new StringBuilder("Group{");
        if (students == null) {
            return "Group doesn't exist.";
        }
        for (int i = 0; i < students.size(); i++) {
            sortedBySurname.append("\n\t").append(students.get(i));
        }
        sortedBySurname.append("}");
        return sortedBySurname.toString();
    }

    public boolean addStudent(Student student) throws RedundantStudentException {
        if (student == null || students == null) return false;
        if (students.contains(student)) {
            System.out.println("This student is including in this group.");
            return false;
        }
        if (students.size() < 10) {
            students.add(student);
            return true;
        } else throw new RedundantStudentException("It's a redundant student.");
    }

    public boolean addStudentFromKeyboard() throws RedundantStudentException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String sex, name, surname;
        int age;
        long recordBook;
        try {
            System.out.println("We have a new student!");
            System.out.println("Please enter sex (male/female)");
            sex = bufferedReader.readLine();
            System.out.println("Please enter name");
            name = bufferedReader.readLine();
            System.out.println("Please enter surname");
            surname = bufferedReader.readLine();
            System.out.println("Please enter age");
            age = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please enter record-book number");
            recordBook = Long.parseLong(bufferedReader.readLine());
            return addStudent(new Student(Sex.valueOf(sex), name, surname, age, recordBook));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeStudent(long recordBook) {
        if (students == null) return false;
        for (int i = 0; i < students.size(); i++) {
            if (recordBook == students.get(i).getRecordBookId()) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public Student findStudent(String surname) {
        if (surname == null || students == null) {
            return null;
        }
        for (Student student : students) {
            if (student.getSurname().equals(surname)) {
                return student;
            }
        }
        return null;
    }

    public File saveToFile(String name) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(this);
            return new File(name);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return new File("");
    }

    public Group readFromFile(String name) {
        Group newGroup = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))) {
            newGroup = (Group) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newGroup;
    }

    public void sortByAge() {
        students.sort(new AgeComparator());
    }

    public void sortByName() {
        students.sort(new NameComparator());
    }

    public void sortBySurname() {
        students.sort(new SurnameComparator());
    }

    @Override
    public List<Student> mobilisation() {
        List<Student> soldiers = new ArrayList<>();
        sortByAge();
        for (Student student : students) {
            if (student.getSex().equals(Sex.male) && student.getAge() >= 18) {
                soldiers.add(student);
            }
        }
        return soldiers;
    }
}

