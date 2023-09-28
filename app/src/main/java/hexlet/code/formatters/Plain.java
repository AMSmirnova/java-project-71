package hexlet.code.formatters;

import hexlet.code.Diffs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String formatPlain(List<Diffs> data) {
        StringBuilder result = new StringBuilder();
        data.forEach((value) -> {
            try {
                result.append(getLine(value));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return result.toString();
    }

    public static String getLine(Diffs data) throws IOException {
        return switch (data.getStatus()) {
            case "added" -> "\nProperty '"
                    + data.getKey()
                    + "' was added with value: "
                    + complexValueToString(data.getValueOld());
            case "deleted" -> "\nProperty '" + data.getKey() + "' was removed";
            case "unchanged" -> "";
            case "changed" -> "\nProperty '"
                    + data.getKey()
                    + "' was updated. From "
                    + complexValueToString(data.getValueOld())
                    + " to "
                    + complexValueToString(data.getValueNew());

            default -> throw new IOException(" ");
        };
    }

    public static String complexValueToString(Object value) {
        if (value instanceof Arrays || value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (value instanceof Boolean) {
            return value.toString();
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}