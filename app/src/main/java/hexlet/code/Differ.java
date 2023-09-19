package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static String generate(Path filepath1, Path filepath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> mapFile1
                = objectMapper.readValue(Files.readString(filepath1), new TypeReference<Map<String,Object>>(){});

        Map<String, Object> mapFile2
                = objectMapper.readValue(Files.readString(filepath2), new TypeReference<Map<String,Object>>(){});

        var result = genDiff(mapFile1, mapFile2);

        return result.toString();
    }

    public static Map<Map<String, Object>, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Map<Map<String, Object>, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key: keys) {
            if (!data1.containsKey(key)) {
                result.put(Map.of(key, data2.get(key)), " + ");
            } else if (!data2.containsKey(key)) {
                result.put(Map.of(key, data1.get(key)), " - ");
            } else if (data1.get(key).equals(data2.get(key))) {
                result.put(Map.of(key, data1.get(key)), "   ");
            } else {
                result.put(Map.of(key, data1.get(key)), " - ");
                result.put(Map.of(key, data2.get(key)), " + ");
            }
        }
        return result;
    }
}

