package club.lemos.common.utils;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileUtil extends FileCopyUtils {

    public static File createTmpFile() {
        File file = createFile(getTmpPath() + MicroUUID.randomUUID(), false);
        if (file.exists()) {
            file.deleteOnExit();
        }
        return file;
    }

    public static File createTmpFileIfNotExist(String filePath) {
        File file = createFile(getTmpPath() + filePath, true);
        if (file.exists()) {
            file.deleteOnExit();
        }
        return file;
    }

    public static File createFile(String filePath, boolean ignoreIfExist) {
        try {
            File file = new File(filePath);
            if (!ignoreIfExist || !file.exists()) {
                File parent = file.getParentFile();
                if (parent != null && !parent.exists()) {
                    boolean isOk = parent.mkdirs();
                }
                boolean isOk = file.createNewFile();
                if (!isOk) {
                    System.out.println("File create fail " + filePath);
                }
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException("io err", e);
        }
    }

    public static void copyURLToFile(URL source, File destination) throws IOException {
        InputStream input = source.openStream();

        try {
            FileOutputStream output = openOutputStream(destination);

            try {
                StreamUtil.copy(input, output);
            } finally {
                StreamUtil.closeQuietly(output);
            }
        } finally {
            StreamUtil.closeQuietly(input);
        }

    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }

            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("File '" + file + "' could not be created");
            }
        }

        return new FileOutputStream(file);
    }

    private static String getTmpPath() {
        return System.getProperty("java.io.tmpdir") + File.separator;
    }

    /**
     * 获取文件后缀名
     *
     * @param fullName 文件全名
     * @return {String}
     */
    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "unknown" : fileName.substring(dotIndex + 1);
    }

    /**
     * 获取文件名，去除后缀名
     *
     * @param file 文件
     * @return {String}
     */
    public static String getNameWithoutExtension(String file) {
        String fileName = new File(file).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }
}
