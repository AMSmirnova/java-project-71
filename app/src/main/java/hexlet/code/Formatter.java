package hexlet.code;

import java.io.IOException;
import java.util.List;

import static hexlet.code.formatters.Json.formatJson;
import static hexlet.code.formatters.Plain.formatPlain;
import static hexlet.code.formatters.Stylish.formatStylish;

public class Formatter {

    public static String setResultFormat(List<Diffs> data, String format) throws IOException {
        return switch (format) {
            case "stylish" -> formatStylish(data);
            case "plain" -> formatPlain(data);
            case "json" -> formatJson(data);

            default -> throw new IOException(format + " wrong format");
        };
    }
}
