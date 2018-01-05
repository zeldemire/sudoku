public class DFS implements Techniques {

    private Puzzle puzzle;

    public DFS(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public boolean Solve() {
        if (puzzle.isDone()) {
            return true;
        }

        Tile tile = puzzle.getNext();

        for (int i = 1; i <= 9; i++) {
            if (puzzle.conflictCheck(tile.getRow(), tile.getCol(), i)) {
                puzzle.addNumToPuzzle(tile.getRow(), tile.getCol(), i);

                if (Solve()) {
                    return true;
                }

                puzzle.removeNumFromPuzzle(tile.getRow(), tile.getCol(), i);
            }

        }
        return false;
    }

    @Override
    public Puzzle getPuzzle() {
        return puzzle;
    }
}
//46108078
//124820