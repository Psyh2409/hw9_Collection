package com.gmail.psyh2409.task1;

import java.io.File;

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
}
