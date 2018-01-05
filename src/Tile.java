import java.util.HashSet;
import java.util.Set;

public class Tile {
    private int row;
    private int col;
    private int num;
    private boolean unChangeable = false;

    private Set<Integer> possible_numbers;

    public Tile(int row, int col, int num) {
        this.row = row;
        this.col = col;
        this.num = num;
        this.possible_numbers = new HashSet<>();

    }

    public Tile(int row, int col, Set<Integer> possible_numbers) {
        this.row = row;
        this.col = col;
        this.possible_numbers = possible_numbers;
    }

    public boolean isUnChangeable() {
        return unChangeable;
    }

    public void setUnChangeable(boolean unChangeable) {
        this.unChangeable = unChangeable;
    }

    public Set<Integer> getPossible_numbers() {
        return possible_numbers;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
