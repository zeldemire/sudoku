import java.nio.file.Path;
import java.nio.file.Paths;

public class Sudoku_Solver {


    public static void main(String[] args) {
        Path path = Paths.get("E:/Documents/School/AI/sudoku/files", "sudoku-1.txt");
        File_Reader n = new File_Reader(path);
        n.readFile();
    }

}
