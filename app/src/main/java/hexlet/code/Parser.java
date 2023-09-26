package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseJson(String fileData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(fileData, new TypeReference<>() { });
    }

    public static Map<String, Object> parseYAML(String fileData) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(fileData, new TypeReference<>() { });
    }

    public static Map<String, Object> parse(String fileData, String format) throws IOException {
        return switch (format) {
            case "json" -> parseJson(fileData);
            case "yml", "yaml" -> parseYAML(fileData);
            default -> throw new IOException(format + " wrong file format");
        };
    }
}
