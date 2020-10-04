package com.gmail.psyh2409.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class DocFiles {
    private String path;

    public DocFiles() {
        super();
    }

    public DocFiles(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File[] findFiles(String extension) {
        File dir = new File(path);
        if (!dir.exists()) System.out.println(path + " doesn't exists.");
        File[] files = dir.listFiles(new MyFileNameFilter(extension));
        if (files == null) return new File[0];
        if (files.length == 0) System.out.println(path + " hasn't files with extension '" + extension + "'.");
        return files;
    }

    public void copyFiles(File dir, File[] files) {
        if (dir == null || files == null) {
            System.out.println("Folder or files is null.");
            return;
        } else {
            if (!dir.exists() & dir.mkdir()) {
                for (File file : files) {
                    if (file != null) {
                        try {
                            Path fileForCopy = file.toPath();
                            Path dirCopyTo = new File(
                                    dir.toString().concat(
                                            fileForCopy.toString().substring(
                                                    fileForCopy.toString().lastIndexOf(File.separator)))).toPath();
                            Files.copy(fileForCopy, dirCopyTo, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
