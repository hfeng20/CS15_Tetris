import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PaneOrganizer {

  private BorderPane root;

  public PaneOrganizer() {
    this.root = new BorderPane();
    this.setupBottom();
    this.setupCenter();
  }

  private void setupCenter() {
    Pane center = new Pane();
    new Game(center);
    root.setCenter(center);
    center.setFocusTraversable(true);
  }

  private void setupBottom() {
    HBox bottom = new HBox();
    Button quit = new Button("Quit");
    bottom.getChildren().addAll(quit);
    this.root.setBottom(bottom);
    quit.setOnAction((ActionEvent e) -> System.exit(0));
    bottom.setFocusTraversable(false);
    quit.setFocusTraversable(false);
  }

  public Pane getRoot() {
    return this.root;
  }
}