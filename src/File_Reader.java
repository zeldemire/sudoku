import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class File_Reader {
    private int row;
    private int col;
    private int dimension;
    private Path file;
    private int[][] puzzle;

    public File_Reader(Path file) {
        this.file = file;
    }

    public void readFile() {
        int i;
        try {
            InputStream in = Files.newInputStream(file);
            while ((i = in.read()) != -1) {
                System.out.println((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int[][] getPuzzle() {
        return puzzle;
    }
}
