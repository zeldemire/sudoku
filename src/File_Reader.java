import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class File_Reader {
    private Path file;
    private List<String> line;

    public File_Reader(Path file) {
        this.file = file;
    }

    public void readFile() {
        try {
            line = Files.lines(file).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLine() {
        return line;
    }
}
