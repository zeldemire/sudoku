import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Sudoku_Solver {


    public static void main(String[] args) {
        Path path = Paths.get("E:/Documents/School/AI/sudoku/files", "sudoku-1.txt");
        Path path2 = Paths.get("E:/Documents/School/AI/sudoku/files", "sudoku-2.txt");
        Path path3 = Paths.get("E:/Documents/School/AI/sudoku/files", "sudoku-3.txt");
        Path path4 = Paths.get("E:/Documents/School/AI/sudoku/files", "sudoku-4.txt");
        Path path5 = Paths.get("E:/Documents/School/AI/sudoku/files", "sudoku-5.txt");
        Path path6 = Paths.get("E:/Documents/School/AI/sudoku/files", "sudoku-6.txt");

        List<Path> paths = new ArrayList<>();

        paths.add(path);
        paths.add(path2);
        paths.add(path3);
        paths.add(path4);
        paths.add(path5);
        paths.add(path6);

        for (Path path1 : paths) {

            File_Reader n = new File_Reader(path1);
            n.readFile();
            Puzzle puzzle = new Puzzle(n.getLine().size(), n.getLine());


            System.out.println(path1.getFileName());
            DFS dfs = new DFS(puzzle);
            long startTime = System.nanoTime();
            dfs.Solve();
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.println("DFS");
            System.out.println(duration + " nanoseconds");
            System.out.println();
            print(dfs.getPuzzle());

            MRV mrv = new MRV(puzzle);
            startTime = System.nanoTime();
            mrv.Solve();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("MRV");
            System.out.println(duration + " nanoseconds");
            System.out.println();
            print(mrv.getPuzzle());

            FC fc = new FC(puzzle);
            startTime = System.nanoTime();
            fc.Solve();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("FC");
            System.out.println(duration + " nanoseconds");
            System.out.println();
            print(fc.getPuzzle());
        }

    }

    public static void print(Puzzle puzzle) {
        for (int i = 0; i < puzzle.getPuzzle().length; i++) {
            for (int j = 0; j < puzzle.getPuzzle().length; j++) {
                System.out.print(puzzle.getPuzzle()[i][j].getNum());
            }
            System.out.println();
        }
        System.out.println();
    }
}
