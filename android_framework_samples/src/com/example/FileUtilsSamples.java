package com.example;

import android.app.Activity;

import blak.android.utils.FileUtils;

import java.io.PrintStream;

public class FileUtilsSamples extends Activity {
    private static final PrintStream out = System.out;

    private void testgetExtension(){
        out.println(FileUtils.getExtension("/"));
        out.println(FileUtils.getExtension("/mnt.not/"));
        out.println(FileUtils.getExtension("/mnt.not/asd.yes1"));
        out.println(FileUtils.getExtension("ghj"));
        out.println(FileUtils.getExtension(".yes2"));
        out.println(FileUtils.getExtension("."));
        out.println(FileUtils.getExtension("file.yes3"));
    }
}
