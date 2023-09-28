package hexlet.code;

import java.io.IOException;
import java.util.Map;

public class Formatter {

    public static String setResultFormat(Map<String, Object> data, String format) throws IOException {
        return switch (format) {
            case "stylish" -> formatStylish(data);

            default -> throw new IOException(format + " wrong format");
        };
    }


    public static String formatStylish(Map<String, Object> data) {
        StringBuilder result = new StringBuilder("{");
        data.forEach((key, value) -> result.append("\n " + value + key));
        result.append("\n}");

        return result.toString();
    }


}
