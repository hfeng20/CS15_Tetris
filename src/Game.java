import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public class Game {
    private Board board;
    private Pane pane;
    private KeyFrame keyframe;
    private Timeline timeline;

    public Game(Pane pane) {
        this.pane = pane;
        this.board = new Board(this.pane);
        Piece testPiece = new Piece(this.pane);
        testPiece.moveDown();
        testPiece.moveDown();
    }

    public void play() {

    }
}