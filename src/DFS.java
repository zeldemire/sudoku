import java.util.Stack;

public class DFS implements Techniques {

    private Puzzle puzzle;

    public DFS(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public void Solve() {
        Stack<Puzzle> stack = new Stack<>();

        stack.push(puzzle);

        while (!stack.empty()) {
            Puzzle newPuzzle = stack.pop();

            Tile tile = newPuzzle.getNext();

            if (newPuzzle.isDone()) {
                break;
            }

            if (tile.getPossible_numbers().size() == 0) {
                newPuzzle.removeNumFromPuzzle(tile.getRow(), tile.getCol() - 1,
                        newPuzzle.getPuzzle()[tile.getRow()][tile.getCol()-1].getNum());
                stack.pop();
                continue;
            }

            int size = tile.getPossible_numbers().size();

            for (int i = 0; i < size; i++) {
                Puzzle temp = new Puzzle(puzzle.getPuzzle());
                temp.addNumToPuzzle(tile.getRow(), tile.getCol(), tile.getPossible_numbers().get(i));
                stack.push(temp);
            }
        }

    }

    @Override
    public Puzzle getPuzzle() {
        return puzzle;
    }
}
