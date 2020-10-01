package com.gmail.psyh2409.task2;

import com.gmail.psyh2409.Main;

import java.io.*;

public class CommonWords {
    private String path;
    private String name;
    private String extension;

    public CommonWords() {
        super();
    }

    public CommonWords(String path, String name, String extension) {
        this.path = path;
        this.name = name;
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return path + File.separator + name + '.' + extension;
    }

    public String[] fileReader(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready())
                stringBuilder.append(br.readLine()).append(" ");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return stringBuilder.toString().split(" ");
    }

    public File fileWriter(File file1, File file2) {
        File file = new File(path + File.separator + name + '.' + extension);
        if (file1 == null || file2 == null) {
            System.out.println("Reading of files is impossible because someone or both is null");
        } else {
            if (file1.exists() && file2.exists()) {
                String[] text1 = fileReader(file1);
                String[] text2 = fileReader(file2);
                if (!file.exists())
                    file = Main.filesFactory(path, 1, name, extension)[0];
                try (PrintWriter pw = new PrintWriter(file)) {
                    for (String s : text1) {
                        for (int i = 0; i < text2.length; i++) {
                            if (text2[i].equals("")) continue;
                            if (s.equalsIgnoreCase(text2[i])) {
                                pw.println(s);
                                text2[i] = "";
                            }
                        }
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else {
                System.out.println("The first or the second file isn't exist.");
            }
        }
        return file;
    }
}
