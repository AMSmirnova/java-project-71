package hexlet.code.formatters;

import hexlet.code.Diffs;

import java.io.IOException;
import java.util.List;

public class Stylish {
    public static String formatStylish(List<Diffs> data) {
        StringBuilder result = new StringBuilder("{");
        data.forEach((value) -> {
            try {
                result.append(getLine(value));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        result.append("\n}");

        return result.toString();
    }

    public static String getLine(Diffs data) throws IOException {
        return switch (data.getStatus()) {
            case "added" -> "\n  + " + data.getKey() + ": " + data.getValueNew();
            case "deleted" -> "\n  - " + data.getKey() + ": " + data.getValueOld();
            case "unchanged" -> "\n    " + data.getKey() + ": " + data.getValueOld();
            case "changed" -> "\n  - "
                    + data.getKey()
                    + ": "
                    + data.getValueOld()
                    + "\n  + "
                    + data.getKey()
                    + ": "
                    + data.getValueNew();

            default -> throw new IOException(" ");
        };
    }
}
