import javafx.scene.layout.Pane;

public class Board {
    private Square[][] board;
    private Pane boardPane;

    public Board(Pane pane) {
        this.boardPane = pane;
        this.board = new Square[14][26];
        for (int i = 0; i < board.length; i++) {
            for (int t = 0; t < board[0].length; t++) {
                Square newSquare = new Square(i, t);
                board[i][t] = newSquare;
                pane.getChildren().addAll(newSquare.getShape());
            }
        }
    }

    public boolean isOver() {
        for (int i = 0; i < 14; i++) {
            if (board[i][1].isOccupied()) {
                return true;
            }
        }
        return false;
    }

    public void clearRows() {
        for (int i = 0; i < 26; i++) {
            boolean shouldClear = true;
            for (int t = 0; t < 14; t++) {
                if (!board[t][i].isOccupied()) {
                    shouldClear = false;
                    break;
                }
            }
            System.out.println(shouldClear);
            if (shouldClear) {
                for (int t = 0; t < 14; t++) {
                    for (int x = i; x > 0; x--) {
                        board[t][x + 1] = board[t][x];
                    }
                    board[t][1] = new Square(1, t);
                }
                i--;
            }
        }
    }

    public Square[][] getSquares() {
        return this.board;
    }
}
