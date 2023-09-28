
package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String fileData1 = getData(filepath1);
        String fileData2 = getData(filepath2);

        var fileFormat1 = getFileFormat(filepath1);
        var fileFormat2 = getFileFormat(filepath2);

        Map<String, Object> mapFile1 = Parser.parse(fileData1, fileFormat1);
        Map<String, Object> mapFile2 = Parser.parse(fileData2, fileFormat2);

        var result = genDiff(mapFile1, mapFile2);

        return Formatter.setResultFormat(result, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String getData(String pathString) throws Exception {
        Path path = Path.of(pathString);
        path.toAbsolutePath().normalize();
        // Проверяем существование файлов
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static String getFileFormat(String filePath) {
        String[] result = filePath.split("\\.");
        return result[result.length - 1];
    }

    public static Map genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Map<String, Object> result = new LinkedHashMap<>();
        Set<Object> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (Object key: keys) {
            if (!data1.containsKey(key)) {
                result.put(key + ": " + data2.get(key), " + ");
            } else if (!data2.containsKey(key)) {
                result.put(key + ": " + data1.get(key), " - ");
//            } else if (data1.get(key).equals(data2.get(key))) {
//                result.put(key + ": " + data1.get(key), "   ");
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                result.put(key + ": " + data1.get(key), "   ");
            } else {
                result.put(key + ": " + data1.get(key), " - ");
                result.put(key + ": " + data2.get(key), " + ");
            }
        }
        return result;
    }
}

