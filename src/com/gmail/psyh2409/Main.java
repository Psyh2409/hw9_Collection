package com.gmail.psyh2409;

import com.gmail.psyh2409.task1.DocFiles;
import com.gmail.psyh2409.task2.CommonWords;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1");
        String pathToDir = "E:" + File.separator + "example";
        DocFiles docFiles = new DocFiles(pathToDir);
        System.out.println("All files in folder '"+pathToDir+"':");
        filesFactory(pathToDir, 4, "doc", "doc");
        filesFactory(pathToDir, 3, "jpg", "jpg");
        printFiles(filesFactory(pathToDir, 3, "pdf", "pdf"));
        System.out.println();
        System.out.println("List of '*.doc' files:");
        printFiles(docFiles.findFiles("doc"));
        docFiles.copyFiles(
                new File("E:" + File.separator + "Psyh" + File.separator + "java" + File.separator + "someFolder"),
                        docFiles.findFiles("doc"));
        System.out.println();

        System.out.println("Task 2");
        File[] files = filesFactory(pathToDir+1, 2, "text", "txt");
        textToFileWriter(files[0], "every day my son is eating chicken meat");
        textToFileWriter(files[1], "every day cannibal is eating human meat");
        CommonWords commonWords = new CommonWords(pathToDir+1, "commonWords", "txt");
        System.out.println("Content of the first file: " + Arrays.toString(commonWords.fileReader(files[0])));
        System.out.println("Content of the second file: " + Arrays.toString(commonWords.fileReader(files[1])));
        System.out.println();
        File file = commonWords.fileWriter(files[0], files[1]);
        System.out.println("Content of the third file: " + Arrays.toString(commonWords.fileReader(file)));

/*
System.out is:
Task 1
D:\java\test\doc0.doc
D:\java\test\doc1.doc
D:\java\test\doc2.doc
D:\java\test\jpg0.jpg
D:\java\test\jpg1.jpg
D:\java\test\jpg2.jpg
D:\java\test\pdf0.pdf
D:\java\test\pdf1.pdf
D:\java\test\pdf2.pdf

D:\java\test\doc0.doc
D:\java\test\doc1.doc
D:\java\test\doc2.doc

Task 2
Content of the first file: [every, day, my, son, is, eating, chicken, meat]
Content of the second file: [every, day, cannibal, is, eating, human, meat]

Content of the third file: [every, day, is, eating, meat]
*/
    }

    public static File[] filesFactory(String path, int quantity, String name, String extension) {
        File folder = new File(path);
        String fileName = path + File.separator + name;
        if (!folder.exists() && folder.mkdir()) {
            for (int i = 0; i < quantity; i++) {
                try {
                    new File(fileName + i + "." + extension).createNewFile();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } else {
            for (int i = 0; i < quantity; i++) {
                if (!new File(fileName + i + "." + extension).exists()) {
                    try {
                        new File(fileName + i + "." + extension).createNewFile();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        }
        return folder.listFiles();
    }

    public static void textToFileWriter(File file, String text){
        if (file == null || text == null) return;
        String[] words = text.split(" ");
        if (file.exists()) {
            try (PrintWriter pw = new PrintWriter(file)) {
                for (String word : words) {
                    pw.println(word);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static void printFiles(File[] files) {
        if (files != null)
            for (File f : files)
                System.out.println(f);
    }
}


