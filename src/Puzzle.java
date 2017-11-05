import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle {

    private Tile[][] puzzle;

    private HashMap<Integer, List<Integer>> row_map;
    private HashMap<Integer, List<Integer>> col_map;

    public Puzzle(int size, List<String> file) {
        row_map = new HashMap<>();
        col_map = new HashMap<>();
        puzzle = new Tile[size][size];

        initMaps();
        populatePuzzle(file, size);

        setupTile();
    }

    public Puzzle(Tile[][] puzzle) {
        row_map = new HashMap<>();
        col_map = new HashMap<>();
        this.puzzle = puzzle;
        initMaps();

        setupTile();
    }

    public Tile[][] getPuzzle() {
        return puzzle;
    }

    private void setupTile() {
        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle.length; col++) {
                if (puzzle[row][col] == null || puzzle[row][col].getNum() == 0) {
                    List<Integer> possibleNumbers = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList());
                    List<Integer> rowNumbers = row_map.get(row);
                    List<Integer> colNumbers = col_map.get(col);
                    List<Integer> boxNumbers = getBoxNumbers(row, col);
                    possibleNumbers.removeIf(num -> rowNumbers.stream().anyMatch(num::equals));
                    possibleNumbers.removeIf(num -> colNumbers.stream().anyMatch(num::equals));
                    possibleNumbers.removeIf(num -> boxNumbers.stream().anyMatch(num::equals));
                    puzzle[row][col] = new Tile(row, col, possibleNumbers);
                }
            }
        }
    }

    private List<Integer> getBoxNumbers(int row, int col) {
        int rowStart = (row - row % 3);
        int colStart = (col - col % 3);

        List<Integer> numbersInBox = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i + rowStart][j + colStart] != null) {
                    numbersInBox.add(puzzle[i + rowStart][j + colStart].getNum());
                }
            }
        }

        return numbersInBox;
    }

    private void initMaps() {
        for (int i = 0; i < puzzle.length; i++) {
            col_map.put(i, new ArrayList<>());
            row_map.put(i, new ArrayList<>());
        }
    }

    private void populatePuzzle(List<String> lines, int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!String.valueOf(lines.get(row).charAt(col)).equals("-")) {
                    int number = Integer.parseInt(String.valueOf(lines.get(row).charAt(col)));
                    col_map.get(col).add(number);
                    row_map.get(row).add(number);
                    puzzle[row][col] = new Tile(row, col, number);
                }
            }
        }
    }

    public Tile getNext() {
        for (Tile[] aPuzzle : puzzle) {
            for (int j = 0; j < puzzle.length; j++) {
                if (aPuzzle[j].getNum() == 0) {
                    return aPuzzle[j];
                }
            }
        }
        return null;
    }

    public boolean isDone() {
        return Arrays.stream(puzzle).flatMap(Arrays::stream).noneMatch(x -> x.getNum() == 0);
    }

    public void addNumToPuzzle(int rowNum, int colNum, int num) {
        updateTile(rowNum, colNum, num);
        puzzle[rowNum][colNum].getPossible_numbers().remove(Integer.valueOf(num));
        addNumToRow(rowNum, num);
        addNumToCol(colNum, num);
        setupTile();
    }

    private void addNumToRow(int rowNum, int num) {
        row_map.get(rowNum).add(num);
    }

    private void addNumToCol(int colNum, int num) {
        col_map.get(colNum).add(num);
    }

    public void removeNumFromPuzzle(int rowNum, int colNum, int num) {
        updateTile(rowNum, colNum, 0);
        puzzle[rowNum][colNum].getPossible_numbers().add(num);
        removeNumFromRow(rowNum, num);
        removeNumFromCol(colNum, num);
        setupTile();
    }

    private void removeNumFromCol(int colNum, int num) {
        col_map.get(colNum).remove(Integer.valueOf(num));
    }

    private void removeNumFromRow(int rowNum, int num) {
        row_map.get(rowNum).remove(Integer.valueOf(num));
    }

    private void updateTile(int rowNum, int colNum, int num) {
        puzzle[rowNum][colNum].setNum(num);
    }

}
