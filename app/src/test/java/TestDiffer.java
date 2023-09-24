import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDiffer {

    @Test
    public void testDiffer() throws IOException {
        Path path1 = Paths.get("src/test/resources/filepath1.json").toAbsolutePath().normalize();
        Path path2 = Paths.get("src/test/resources/filepath2.json").toAbsolutePath().normalize();

        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";

        assertThat(Differ.generate(path1, path2)).isEqualTo(expected);

    }
}
