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
                pane.getChildren().addAll(newSquare.getShape());
            }
        }
    }
}
