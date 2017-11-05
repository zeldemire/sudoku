public class FC implements Techniques {

    private Puzzle puzzle;

    public FC(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public void Solve() {
    }

    @Override
    public Puzzle getPuzzle() {
        return puzzle;
    }
}
