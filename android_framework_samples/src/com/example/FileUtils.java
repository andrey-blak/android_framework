package com.example;

import android.content.Context;
import android.os.Environment;

import java.io.*;

// TODO check
public class FileUtils {
    private FileUtils() {
    }

    public static boolean isSDCardWritable() {
        String state = Environment.getExternalStorageState();
        boolean writable = Environment.MEDIA_MOUNTED.equals(state) && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
        return writable;
    }

    public static File externalDirectory(String appDirectory) throws IOException {
        File dir = new File(Environment.getExternalStorageDirectory(), appDirectory);
        if (!dir.exists()) {
            //if (!dir.mkdirs())
            {
                throw new IOException("IOExeption:" + dir.getAbsoluteFile());
            }
        }
        return dir;
    }

    public static void saveInternal(InputStream input, String fileName, Context context) throws IOException {
        OutputStream output = context.openFileOutput(fileName, Context.MODE_WORLD_WRITEABLE);
        copy(input, output);
    }

    public static void saveExternal(InputStream input, String fileName, Context context, String appName) throws IOException {
        File dir = externalDirectory(appName);
        OutputStream output = new FileOutputStream(new File(dir, fileName));

        copy(input, output);
    }

    public static void copy(InputStream from, OutputStream to) throws IOException {
        int buffersize = 10 * 1024;
        byte[] buffer = new byte[buffersize];

        for (int bytes = from.read(buffer); bytes >= 0; bytes = from.read(buffer)) {
            to.write(buffer, 0, bytes);
        }

        to.flush();
        to.close();
    }
}
