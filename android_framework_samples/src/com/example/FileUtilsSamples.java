package com.example;

import android.app.Activity;
import android.os.Bundle;

import blak.android.utils.FileUtils;
import blak.android.utils.MyLog;

public class FileUtilsSamples extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testGetExtension();
    }

    private void testGetExtension() {
        MyLog.v(FileUtils.getExtension("/"));
        MyLog.v(FileUtils.getExtension("/mnt.not/"));
        MyLog.v(FileUtils.getExtension("/mnt.not/asd.yes1"));
        MyLog.v(FileUtils.getExtension("ghj"));
        MyLog.v(FileUtils.getExtension(".yes2"));
        MyLog.v(FileUtils.getExtension("."));
        MyLog.v(FileUtils.getExtension("file.yes3"));
    }
}
