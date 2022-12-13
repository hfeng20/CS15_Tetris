import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PaneOrganizer {

  private BorderPane root;
  private Label label;

  public PaneOrganizer() {
    this.root = new BorderPane();
    this.setupBottom();
    this.setupCenter();
  }

  private void setupCenter() {
    Pane center = new Pane();
    new Game(center, this.label);
    root.setCenter(center);
    center.setFocusTraversable(true);
  }

  private void setupBottom() {
    this.label = new Label("Tetris");
    this.label.setStyle("-fx-font-size: 20; -fx-text-fill: black;");
    VBox bottom = new VBox();
    bottom.setAlignment(Pos.CENTER);
    Button quit = new Button("Quit");
    bottom.getChildren().addAll(this.label, quit);
    this.root.setBottom(bottom);
    quit.setOnAction((ActionEvent e) -> System.exit(0));
    bottom.setFocusTraversable(false);
    quit.setFocusTraversable(false);
  }

  public Pane getRoot() {
    return this.root;
  }
}