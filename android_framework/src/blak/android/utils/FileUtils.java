package blak.android.utils;

public class FileUtils {
    public static String getExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        String extension = "";
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
        if (i > p) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}
