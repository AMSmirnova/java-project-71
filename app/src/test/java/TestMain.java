import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;


public class TestMain {
    private static Path pathStylish = Path.of("src/test/resources/expected/stylish.txt");
    private static String expectedStylish;
    private static Path pathPlain = Path.of("src/test/resources/expected/plain.txt");
    private static String expectedPlain;
    private static Path pathJson = Path.of("src/test/resources/expected/json.json");
    private static String expectedJson;
    private static Path pathEmpty = Path.of("src/test/resources/expected/oneEmptyStylish.txt");
    private static String expectedOneEmpty;

    @BeforeAll
    public static void beforeAll() throws IOException {
        expectedStylish = Files.readString(pathStylish);
        expectedPlain = Files.readString(pathPlain);
        expectedJson = Files.readString(pathJson);
        expectedOneEmpty = Files.readString(pathEmpty);
    }

    @Test
    public void testWrongPath() {
        var thrown = catchThrowable(
                () -> Differ.generate("newfile1.json", "newfile2.json")
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void testEmptyFile() throws Exception {
        String path = "src/test/resources/empty.json";
        String path2 = "src/test/resources/file2.json";
        assertThat(Differ.generate(path, path)).isEqualTo("{\n}");
        assertThat(Differ.generate(path, path2)).isEqualTo(expectedOneEmpty);
    }

    @Test
    public void testJson() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";

        assertThat(Differ.generate(path1, path2)).isEqualTo(expectedStylish);
        assertThat(Differ.generate(path1, path2, "stylish")).isEqualTo(expectedStylish);
        assertThat(Differ.generate(path1, path2, "plain")).isEqualTo(expectedPlain);
        assertThat(Differ.generate(path1, path2, "json")).isEqualTo(expectedJson);
    }

    @Test
    public void testYML() throws Exception {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";

        assertThat(Differ.generate(path1, path2)).isEqualTo(expectedStylish);
        assertThat(Differ.generate(path1, path2, "stylish")).isEqualTo(expectedStylish);
        assertThat(Differ.generate(path1, path2, "plain")).isEqualTo(expectedPlain);
        assertThat(Differ.generate(path1, path2, "json")).isEqualTo(expectedJson);
    }
}
