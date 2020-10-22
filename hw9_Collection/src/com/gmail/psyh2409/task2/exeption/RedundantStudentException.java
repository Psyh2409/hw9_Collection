package com.gmail.psyh2409.task2.exeption;

public class RedundantStudentException extends Exception {

    public RedundantStudentException() {
        super();
    }

    public RedundantStudentException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public String getMessage() {
        return "It's a redundant student.";
    }
}
