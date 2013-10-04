package blak.android.utils;

import android.content.Context;
import android.os.Environment;

import java.io.*;

public class FileUtils {
    private FileUtils() {
    }

    public static boolean isSDCardWritable() {
        String state = Environment.getExternalStorageState();
        boolean writable = Environment.MEDIA_MOUNTED.equals(state) && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
        return writable;
    }

    public static void printFileException(File file) {
        IOException ioException = new IOException("IOExeption:" + file.getAbsoluteFile());
        ioException.printStackTrace();
    }

    public static File externalDirectory(String appDirectory) {
        File dir = new File(Environment.getExternalStorageDirectory(), appDirectory);
        if (!dir.exists()) {
            //if (!dir.mkdirs())
            {
                printFileException(dir);
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
