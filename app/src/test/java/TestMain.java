
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static hexlet.code.Differ.generate;
import static hexlet.code.Differ.getData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;


public class TestMain {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private static String expectedOneEmpty;

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedStylish = getData("src/test/resources/expected/stylish.txt");
        expectedPlain = getData("src/test/resources/expected/plain.txt");
        expectedJson = getData("src/test/resources/expected/json.json");
        expectedOneEmpty = getData("src/test/resources/expected/oneEmptyStylish.txt");
    }

    @Test
    public void testWrongPath() {
        var thrown = catchThrowable(
                () -> generate("newfile1.json", "newfile2.json")
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void testEmptyFile() throws Exception {
        String path = "src/test/resources/empty.json";
        String path2 = "src/test/resources/file2.json";
        assertThat(generate(path, path)).isEqualTo("{\n}");
        assertThat(generate(path, path2)).isEqualTo(expectedOneEmpty);
    }

    @Test
    public void testJson() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";

        assertThat(generate(path1, path2)).isEqualTo(expectedStylish);
        assertThat(generate(path1, path2, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(path1, path2, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(path1, path2, "json")).isEqualTo(expectedJson);
    }

    @Test
    public void testYML() throws Exception {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";

        assertThat(generate(path1, path2)).isEqualTo(expectedStylish);
        assertThat(generate(path1, path2, "stylish")).isEqualTo(expectedStylish);
        assertThat(generate(path1, path2, "plain")).isEqualTo(expectedPlain);
        assertThat(generate(path1, path2, "json")).isEqualTo(expectedJson);
    }
}
