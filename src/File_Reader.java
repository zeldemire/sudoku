import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class File_Reader {
    private Path file;
    private List<String> line;

    File_Reader(Path file) {
        this.file = file;
    }

    public void readFile() {
        try {
            Stream<String> fileLines = Files.lines(file);
            line = fileLines.map(x -> x.replaceAll("-", "0")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String > getLine() {
        return line;
    }
}
