package com.gmail.psyh2409.task1;

import java.io.File;
import java.io.FilenameFilter;

public class MyFileNameFilter implements FilenameFilter {
    String extension;

    public MyFileNameFilter(String extension) {
        super();
        this.extension = extension;
    }


    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(extension);
    }
}
