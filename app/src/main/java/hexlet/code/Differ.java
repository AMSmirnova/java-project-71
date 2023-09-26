
package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
//        System.out.println(filepath1);
//        System.out.println(filepath2);

        String fileData1 = getData(filepath1);
        String fileData2 = getData(filepath2);

        var format1 = getFormat(filepath1);
        var format2 = getFormat(filepath2);

        Map<String, Object> mapFile1 = Parser.parse(fileData1, format1);
        Map<String, Object> mapFile2 = Parser.parse(fileData2, format2);

        var result = genDiff(mapFile1, mapFile2);

        return mapToString(result);
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

    public static String getFormat(String filePath) {
        String[] result = filePath.split("\\.");
//        System.out.println(result[result.length - 1]);
        return result[result.length - 1];
    }

    public static Map genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Map<String, Object> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key: keys) {
            if (!data1.containsKey(key)) {
                result.put(key + ": " + data2.get(key), " + ");
            } else if (!data2.containsKey(key)) {
                result.put(key + ": " + data1.get(key), " - ");
            } else if (data1.get(key).equals(data2.get(key))) {
                result.put(key + ": " + data1.get(key), "   ");
            } else {
                result.put(key + ": " + data1.get(key), " - ");
                result.put(key + ": " + data2.get(key), " + ");
            }
        }
        return result;
    }

    public static String mapToString(Map<String, Object> contentMap) {
        StringBuilder result = new StringBuilder("{");
        contentMap.forEach((key, value) -> result.append("\n " + value + key));
        result.append("\n}");

        return result.toString();
    }
}

