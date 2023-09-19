package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file.", paramLabel = "filepath1")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file.", paramLabel = "filepath2")
    private String filepath2;
    @Option(names = {"--format", "-f"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]", paramLabel = "format")
    String format;
    @Override
    public Integer call() throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        // Проверяем существование файлов
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }

        var diff = Differ.generate(path1, path2);
        System.out.println(diff);
//        System.out.println(Files.readString(path1));

        return null;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}