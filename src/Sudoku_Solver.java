import java.nio.file.Path;
import java.nio.file.Paths;

public class Sudoku_Solver {


    public static void main(String[] args) {
        Path path = Paths.get("E:/Documents/School/AI/sudoku/files", "sudoku-1.txt");
        File_Reader n = new File_Reader(path);
        n.readFile();
        Puzzle puzzle = new Puzzle(n.getLine().size(), n.getLine());

        DFS dfs = new DFS(puzzle);
        dfs.Solve();
        print(dfs.getPuzzle());
//        MRV mrv = new MRV(puzzle);
//        FC fc = new FC(puzzle);

    }

    public static void print(Puzzle puzzle) {
        for (int i = 0; i < puzzle.getPuzzle().length; i++) {
            for (int j = 0; j < puzzle.getPuzzle().length; j++) {
                System.out.print(puzzle.getPuzzle()[i][j].getNum());
            }
            System.out.println();
        }
    }
}
