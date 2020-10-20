package com.gmail.psyh2409.studentsGroup;

import java.io.Serializable;
import java.util.Objects;

public class Student extends Human implements Serializable {
    private static final long serialVersionUID = 1L;
    private long recordBookId;

    public Student() {
        super();
    }

    public Student(String surname, long recordBookId) {
        super(surname);
        this.recordBookId = recordBookId;
    }

    public Student(Sex sex, String name, String surname, int age, long recordBookId) {
        super(sex, name, surname, age);
        this.recordBookId = recordBookId;
    }

    public long getRecordBookId() {
        return recordBookId;
    }

    public void setRecordBookId(long recordBookId) {
        this.recordBookId = recordBookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return recordBookId == student.recordBookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), recordBookId);
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                " and recordBookId=" + recordBookId +
                '}';
    }

    @Override
    public void printInfo() {
        System.out.println(this);
    }
}
