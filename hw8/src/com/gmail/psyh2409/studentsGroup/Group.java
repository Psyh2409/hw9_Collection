package com.gmail.psyh2409.studentsGroup;

import com.gmail.psyh2409.studentsGroup.comparators.AgeComparator;
import com.gmail.psyh2409.studentsGroup.comparators.NameComparator;
import com.gmail.psyh2409.studentsGroup.comparators.SurnameComparator;
import com.gmail.psyh2409.studentsGroup.exception.RedundantStudentException;

import java.io.*;
import java.util.Arrays;

public class Group implements MilitaryCommissar, Serializable {
    private static final long serialVersionUID = 1L;
    private Student[] students;

    public Group() {
        super();
        this.students = new Student[10];
    }

    public Group(Student[] students) {
        this.students = students;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Arrays.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(students);
    }

    @Override
    public String toString() {
        customSortBySurname();
        StringBuilder sortedBySurname = new StringBuilder("Group{");
        if (students == null) {
            return "Group doesn't exist.";
        }
        for (int i = 0; i < students.length; i++) {
            sortedBySurname.append("\n\t").append(students[i]);
        }
        sortedBySurname.append("}");
        return sortedBySurname.toString();
    }

    public boolean addStudent(Student student) throws RedundantStudentException {
        if (student == null || students == null) return false;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                return true;
            }
            if (i == students.length - 1) throw new RedundantStudentException();
        }
        return false;
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
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) continue;
            if (students[i].getRecordBookId() == recordBook) {
                students[i] = null;
                return true;
            }
        }
        return false;
    }

    public Student findStudent(String surname) {
        if (surname == null || students == null) {
            return null;
        }
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) continue;
            if (students[i].getSurname().equals(surname)) {
                return students[i];
            }
        }
        return null;
    }

    public File saveToFile(String name){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))){
            oos.writeObject(this);
            return new File(name);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return new File("");
    }

    public Group readFromFile(String name){
        Group newGroup = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))){
            newGroup = (Group) ois.readObject();
        }catch (IOException  | ClassNotFoundException e){
            e.printStackTrace();
        }

        return newGroup;

    }

    public void sortByAge() {
        Arrays.sort(students, new AgeComparator());
    }

    public void sortByName() {
        Arrays.sort(students, new NameComparator());
    }

    public void sortBySurname() {
        Arrays.sort(students, new SurnameComparator());
    }

    private void customSortBySurname() {
        Student temp;
        sortByNull();
        if (students == null) return;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) break;
            for (int j = i + 1; j < students.length; j++) {
                if (students[j] == null) break;
                if (students[i].getSurname().compareTo(students[j].getSurname()) > 0) {
                    temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
    }

    private void sortByNull() {
        if (students == null) return;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                for (int j = i + 1; j < students.length; j++) {
                    if (students[j] == null) continue;
                    students[i] = students[j];
                    if (j == students.length - 1 && students[i].equals(students[j])) {
                        students[j] = null;
                    }
                }
            }
        }
    }

    @Override
    public Student[] mobilisation() {
        Student[] soldiers;
        int count = 0;
        sortByAge();
        for (Student student : students) {
            if (student == null) continue;
            if (student.getSex().equals(Sex.male) && student.getAge() >= 18) {
                count++;
            }
        }
        soldiers = new Student[count];
        for (int i = 0, j = 0; i < students.length; i++) {
            if (students[i] == null) continue;
            if (students[i].getSex().equals(Sex.male) && students[i].getAge() >= 18) {
                soldiers[j] = students[i];
                j++;
            }
        }
        return soldiers;
    }
}
