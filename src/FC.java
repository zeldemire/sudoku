public class FC implements Techniques {

    private Puzzle puzzle;

    public FC(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public boolean Solve() {
        if (puzzle.isDone()) {
            return true;
        }
        Tile tile = puzzle.getNext();
        for (int i = 1; i <= 9; i++) {
            if (tile.getPossible_numbers().contains(i)) {
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
