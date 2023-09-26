import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TestMain {
    @Test
    public void testDiffer() throws Exception {

        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";

        String path1 = "src/test/resources/filepath1.json";
        String path2 = "src/test/resources/filepath2.json";

        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);

        path1 = "src/test/resources/filepath1.yml";
        path2 = "src/test/resources/filepath2.yml";

        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);
    }
}
