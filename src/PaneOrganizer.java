import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
    center.setOnKeyPressed(KeyEvent -> handleKeyPress(KeyEvent));
  }

  private Object handleKeyPress(KeyEvent keyEvent) {
    return null;
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