import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Game {
    private Board board;
    private Pane pane;
    private KeyFrame keyframe;
    private Timeline timeline;
    private Piece currentPiece;

    public Game(Pane pane) {
        this.pane = pane;
        this.board = new Board(this.pane);
        this.currentPiece = new Piece(this.pane);
        this.setupTimeline();
        pane.setOnKeyPressed(KeyEvent -> handleKeyPress(KeyEvent));
    }

    public void action() {
        Square[][] currentSquares = this.board.getSquares();
        int[][] currentCoords = this.currentPiece.getCoords();
        boolean canMoveDown = true;
        for (int[] coord : currentCoords) {
            if (coord[0] == 23 || currentSquares[coord[1] + 6][coord[0] + 2].isOccupied()) {
                canMoveDown = false;
            }
        }
        if (canMoveDown) {
            this.currentPiece.moveDown();
        } else {
            for (int[] coord : currentCoords) {
                currentSquares[coord[1] + 6][coord[0] + 1].setOccupied();
            }
            this.currentPiece = new Piece(this.pane);
        }
    }

    public void moveRight() {
        Square[][] currentSquares = this.board.getSquares();
        int[][] currentCoords = this.currentPiece.getCoords();
        boolean canMoveRight = true;
        for (int[] coord : currentCoords) {
            if (coord[1] + 6 == 12 || currentSquares[coord[1] + 6 + 1][coord[0] + 1].isOccupied()) {
                canMoveRight = false;
            }
        }
        if (canMoveRight) {
            this.currentPiece.moveRight();
        }
    }

    public void moveLeft() {
        Square[][] currentSquares = this.board.getSquares();
        int[][] currentCoords = this.currentPiece.getCoords();
        boolean canMoveLeft = true;
        for (int[] coord : currentCoords) {
            if (coord[1] + 6 == 1 || currentSquares[coord[1] + 6 - 1][coord[0] + 1].isOccupied()) {
                canMoveLeft = false;
            }
        }
        if (canMoveLeft) {
            this.currentPiece.moveLeft();
        }
    }

    private void setupTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(.2),
                (ActionEvent e) -> this.action());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        if (keyPressed == KeyCode.RIGHT) {
            this.moveRight();
        } else if (keyPressed == KeyCode.LEFT) {
            this.moveLeft();
        }
        e.consume();
    }
}