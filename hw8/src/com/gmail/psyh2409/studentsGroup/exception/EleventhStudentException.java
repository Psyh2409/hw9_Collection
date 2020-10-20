package com.gmail.psyh2409.studentsGroup.exception;

public class EleventhStudentException extends Exception {

    public EleventhStudentException() {super();}

    public EleventhStudentException(String message) {super(message);}

    @Override
    public String getMessage() {
        return "The eleventh student is superfluous!";
    }
}
