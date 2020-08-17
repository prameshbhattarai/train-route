package crea.train.route.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author pramesh-bhattarai
 */
public final class FileReaderUtils {

    private FileReaderUtils() {}

    public static InputStream getFileInputStream(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        return new FileInputStream(file);
    }

    public static String inputStreamToString(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

}
