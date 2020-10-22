package com.gmail.psyh2409.task3;

import com.gmail.psyh2409.task3.comparator.LengthComparator;

import java.io.*;
import java.nio.CharBuffer;
import java.util.*;

public class LetterCounter {
    private List<String> strings;
    private File file;

    public LetterCounter() {
        super();
    }

    public LetterCounter(String file) {
        this.strings = new ArrayList<>();
        this.file = new File(file);
        this.toListOfStrings(this.sortSymbols(this.fileReader()));

    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String fileReader() {
        StringBuilder sb = new StringBuilder("");
        int symbol;
        String str = "";
        char[] chars;
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                symbol = br.read();
                while (symbol != -1) {
                    sb.append((char) symbol);
                    symbol = br.read();
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public char[] sortSymbols(String str) {
        if (str == null) {
            return null;
        }
        String s = str.toLowerCase().replaceAll("\\s", "");
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return chars;
    }

    public void toListOfStrings(char[] chars){
        if (chars == null) { return; }
        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i < chars.length; i++) {
            sb.append(chars[i-1]);
            if (chars[i-1] != chars[i]) {
                strings.add(sb.toString());
                sb = new StringBuilder("");
            }
        }
        sortListByLength();
        print();
    }

    public void sortListByLength(){
        strings.sort(new LengthComparator());
    }

    public void print(){
        for (String s: strings) {
            System.out.println(s.charAt(0) + " - " + s.length());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LetterCounter that = (LetterCounter) o;
        return Objects.equals(strings, that.strings) &&
                Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings, file);
    }

    @Override
    public String toString() {
        return "LetterCounter{" +
                "strings=" + strings +
                ", file=" + file +
                '}';
    }
}
