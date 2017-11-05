import java.util.List;

public class Tile {
    private int row;
    private int col;
    private int num;

    private List<Integer> possible_numbers;

    public Tile(int row, int col, int num) {
        this.row = row;
        this.col = col;
        this.num = num;
    }

    public Tile(int row, int col, List<Integer> possible_numbers) {
        this.row = row;
        this.col = col;
        this.possible_numbers = possible_numbers;
    }

    public List<Integer> getPossible_numbers() {
        return possible_numbers;
    }

    public void removeNum(int num) {
        this.possible_numbers.remove(num);
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
