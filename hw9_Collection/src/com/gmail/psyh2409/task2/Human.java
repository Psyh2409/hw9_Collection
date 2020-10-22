package com.gmail.psyh2409.task2;

import java.io.Serializable;
import java.util.Objects;

public class Human implements Serializable {
    private static final long serialVersionUID = 1L;
    private Sex sex;
    private String name;
    private String surname;
    private int age;

    public Human() {
        super();
    }

    public Human(String surname) {
        this.surname = surname;
    }

    public Human(Sex sex, String name, String surname, int age) {
        this.sex = sex;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age &&
                sex == human.sex &&
                name.equals(human.name) &&
                surname.equals(human.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sex, name, surname, age);
    }

    @Override
    public String toString() {
        return "Human{" +
                "sex=" + sex +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    public void printInfo() {
        System.out.println(this);
    }
}
