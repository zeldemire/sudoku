import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle {

    private Tile[][] puzzle;

    private HashMap<Integer, Set<Integer>> row_map = new HashMap<>();
    private HashMap<Integer, Set<Integer>> col_map = new HashMap<>();

    public Puzzle(int size, List<String> file) {
        puzzle = new Tile[size][size];

        initMaps();

        populatePuzzle(file, size);

        setupTile();
    }

    public Tile[][] getPuzzle() {
        return puzzle;
    }

    public boolean conflictCheck(int row, int col, int num) {
        return !row_map.get(row).contains(num) &&
                !col_map.get(col).contains(num) &&
                !getBoxNumbers(row,col).contains(num);
    }
    private void setupTile() {
        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle.length; col++) {
                if (puzzle[row][col] == null || puzzle[row][col].getNum() == 0) {
                    puzzle[row][col].getPossible_numbers().addAll(getBoxNumbers(row, col));
                    puzzle[row][col].getPossible_numbers().addAll(row_map.get(row));
                    puzzle[row][col].getPossible_numbers().addAll(col_map.get(col));
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
                if (puzzle[i + rowStart][j + colStart].getNum() != 0) {
                    numbersInBox.add(puzzle[i + rowStart][j + colStart].getNum());
                }
            }
        }

        return numbersInBox;
    }

    private void initMaps() {
        IntStream.rangeClosed(0,8).forEach(index -> {
            col_map.put(index, new HashSet<>());
            row_map.put(index, new HashSet<>());
        });
    }

    private void populatePuzzle(List<String> lines, int size) {
//        initMaps();

        // Populate the puzzle according to the input
        IntStream.range(0, puzzle.length)
                .forEach(x -> Arrays.setAll(puzzle[x], y -> new Tile(x, y, Integer.parseInt(String.valueOf(lines.get(x)
                        .charAt(y))))));

        // Set non 0's in the puzzle to true
        Arrays.stream(puzzle).flatMap(Arrays::stream).forEach(x -> {
            if (x.getNum() != 0) {
                x.setUnChangeable(true);
            }
        });


        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!String.valueOf(lines.get(row).charAt(col)).equals("0")) {
                    int number = Integer.parseInt(String.valueOf(lines.get(row).charAt(col)));
                    col_map.get(col).add(number);
                    row_map.get(row).add(number);
                    puzzle[row][col] = new Tile(row, col, number);
                }
            }
        }
    }

    public Tile getNext() {

        return Arrays.stream(puzzle).flatMap(Arrays::stream).filter(x -> x.getNum() == 0).findFirst().get();
//        for (Tile[] aPuzzle : puzzle) {
//            for (int j = 0; j < puzzle.length; j++) {
//                if (aPuzzle[j].getNum() == 0) {
//                    return aPuzzle[j];
//                }
//            }
//        }
//        return null;
    }

    public Tile getNextMin() {
//        Tile min = getNext();
        return Arrays.stream(puzzle).flatMap(Arrays::stream)
                .min(Comparator.comparingInt(x -> !x.isUnChangeable() ? x.getPossible_numbers().size() : 10))
                .get();
//        for (Tile[] aPuzzle : puzzle) {
//            for (int j = 0; j < puzzle.length; j++) {
//                if (aPuzzle[j].getPossible_numbers() != null) {
//                    if (min.getPossible_numbers().size() > aPuzzle[j].getPossible_numbers().size()) {
//                        min = aPuzzle[j];
//                    }
//                }
//            }
//        }
//        return min;
    }
    public boolean isDone() {
        return Arrays.stream(puzzle).flatMap(Arrays::stream).noneMatch(x -> x.getNum() == 0);
    }

    public void addNumToPuzzle(int rowNum, int colNum, int num) {
        updateTile(rowNum, colNum, num);
        updateMaps(rowNum, colNum, num, true);
//        populatePuzzle(convertToNum(), 9);
//        setupTile();
    }

    public void removeNumFromPuzzle(int rowNum, int colNum, int num) {
        updateTile(rowNum, colNum, 0);
        updateMaps(rowNum, colNum, num, false);
//        populatePuzzle(convertToNum(), 9);
//        setupTile();
    }

    private void updateMaps(int rowNum, int colNum, int num, boolean modifier) {
        if (modifier) {
            row_map.get(rowNum).add(num);
            col_map.get(colNum).add(num);
            addNumToPossibleNums(rowNum, colNum, num);
        } else {
            row_map.get(rowNum).removeIf(x -> x == num);
            col_map.get(colNum).removeIf(x -> x == num);
            removeNumToPossibleNums(rowNum, colNum, num);
        }
    }

    private void removeNumToPossibleNums(int rowNum, int colNum, int num) {
        Arrays.stream(puzzle[rowNum]).takeWhile(Tile::isUnChangeable)
                .forEach(x -> x.getPossible_numbers().add(num));

        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i][colNum].isUnChangeable()) {
                puzzle[i][colNum].getPossible_numbers().add(num);
            }
        }


        int rowStart = (rowNum - rowNum % 3);
        int colStart = (colNum - colNum % 3);

        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (puzzle[i][j].isUnChangeable()) {
                    puzzle[i][j].getPossible_numbers().add(num);
                }
            }
        }
    }

    private void addNumToPossibleNums(int rowNum, int colNum, int num) {
        Arrays.stream(puzzle[rowNum]).takeWhile(Tile::isUnChangeable)
                .forEach(x -> x.getPossible_numbers().removeIf(y -> y == num));

        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i][colNum].isUnChangeable()) {
                puzzle[i][colNum].getPossible_numbers().removeIf(x -> x == num);
            }
        }


        int rowStart = (rowNum - rowNum % 3);
        int colStart = (colNum - colNum % 3);

        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (puzzle[i][j].isUnChangeable()) {
                    puzzle[i][j].getPossible_numbers().removeIf(x -> x == num);
                }
            }
        }
    }

    private void updateTile(int rowNum, int colNum, int num) {
        puzzle[rowNum][colNum].setNum(num);
    }

}
