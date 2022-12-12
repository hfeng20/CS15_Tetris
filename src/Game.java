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
    private Piece currentPiece;
    private boolean playing = true;
    private Timeline timeline;
    private int[] currentPieceCoords = { 0, 0 };

    public Game(Pane pane) {
        this.pane = pane;
        this.board = new Board(this.pane);
        this.currentPiece = new Piece(this.pane);
        this.setupTimeline();
        pane.setOnKeyPressed(KeyEvent -> handleKeyPress(KeyEvent));
    }

    public void moveDown() {
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
            this.currentPieceCoords[1]++;
        } else {
            for (int[] coord : currentCoords) {
                currentSquares[coord[1] + 6][coord[0] + 1].setOccupied();
                currentSquares[coord[1] + 6][coord[0] + 1].setColor(this.currentPiece.getColor());
            }
            if (board.isOver()) {
                this.timeline.stop();
                this.reset();
                return;
            }
            this.board.clearRows();
            this.reset();
            this.currentPiece.removeFromPane();
            this.currentPiece = new Piece(this.pane);
        }
    }

    public void moveRight() {
        Square[][] currentSquares = this.board.getSquares();
        int[][] currentCoords = this.currentPiece.getCoords();
        boolean canMoveRight = true;
        for (int[] coord : currentCoords) {
            if (coord[1] + 6 >= 12 || currentSquares[coord[1] + 6 + 1][coord[0] + 1].isOccupied()) {
                canMoveRight = false;
            }
        }
        if (canMoveRight) {
            this.currentPiece.moveRight();
            this.currentPieceCoords[0]++;
        }
    }

    public void moveLeft() {
        Square[][] currentSquares = this.board.getSquares();
        int[][] currentCoords = this.currentPiece.getCoords();
        boolean canMoveLeft = true;
        for (int[] coord : currentCoords) {
            if (coord[1] + 6 <= 1 || currentSquares[coord[1] + 6 - 1][coord[0] + 1].isOccupied()) {
                canMoveLeft = false;
            }
        }
        if (canMoveLeft) {
            this.currentPiece.moveLeft();
            this.currentPieceCoords[0]--;
        }
    }

    public void moveDownCompletely() {
        Piece currPiece = this.currentPiece;
        while (this.currentPiece.equals(currPiece)) {
            this.moveDown();
        }
    }

    public void rotate() {
        int[][] originalCoords = this.currentPiece.getOriginalCoords();
        Square[][] currentSquares = this.board.getSquares();
        boolean canRotate = true;
        for (int[] coord : originalCoords) {
            int temp1 = coord[0];
            int temp0 = -1 * coord[1];
            if (temp0 + this.currentPieceCoords[1] + 1 < 1 || temp0 + this.currentPieceCoords[1] + 1 > 23
                    || temp1 + this.currentPieceCoords[0] + 6 <= 1 || temp1 + this.currentPieceCoords[0] + 6 > 12
                    || currentSquares[temp1 + this.currentPieceCoords[0] + 6][temp0 + this.currentPieceCoords[1] + 1]
                            .isOccupied()) {
                canRotate = false;
                break;
            }
        }
        if (canRotate) {
            this.currentPiece.rotate(this.currentPieceCoords[0], this.currentPieceCoords[1]);
        }
    }

    private void setupTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(.5),
                (ActionEvent e) -> this.moveDown());
        this.timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        if (keyPressed == KeyCode.RIGHT && this.playing) {
            this.moveRight();
        } else if (keyPressed == KeyCode.LEFT && this.playing) {
            this.moveLeft();
        } else if (keyPressed == KeyCode.SPACE && this.playing) {
            this.moveDownCompletely();
        } else if (keyPressed == KeyCode.UP && this.playing) {
            this.rotate();
        } else if (keyPressed == KeyCode.P) {
            if (this.playing) {
                this.timeline.pause();
            } else {
                this.timeline.play();
            }
            this.playing = !this.playing;
        }
        e.consume();
    }

    private void reset() {
        this.currentPieceCoords = new int[] { 0, 0 };
    }
}