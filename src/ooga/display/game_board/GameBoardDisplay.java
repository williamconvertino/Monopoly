package ooga.display.game_board;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.game_board.board.Board;
import ooga.display.game_board.bottom.Bottom;
import ooga.display.game_board.left.Left;
import ooga.display.game_board.right.Right;
import ooga.display.game_board.top.Top;

import java.util.Random;
import java.util.ResourceBundle;

/**
 * This class displays all elements of the Game BoardManager
 *
 * @author Aaric Han
 * @author Henry Huynh
 */
public class GameBoardDisplay extends Display {

  private static final String DEFAULT_RESOURCE_PACKAGE = Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "gameboard.css";

  private BorderPane theGameBoard;
  private Board theBoard;
  private Top theTop;
  private Right theRight;
  private Left theLeft;
  private Bottom theBottom;

  private Stage myStage;
  private DisplayManager myDisplayManager;
  private Scene scene;

  private ResourceBundle myLanguage;
  /**
   * This constructor makes theGameBoard borderpane with all
   * elements top, left, right, bottom, and center
   */
  public GameBoardDisplay(Stage stage, DisplayManager displayManager, ResourceBundle language) {
    myLanguage = language;
    myStage = stage;
    myDisplayManager = displayManager;
    theTop = new Top(this, myDisplayManager, myLanguage);
    theRight = new Right(this, myDisplayManager, myLanguage);
    theLeft = new Left(this, myDisplayManager, myLanguage);
    theBottom = new Bottom(this, myDisplayManager, myLanguage);
    theBoard = new Board(this, myDisplayManager, myLanguage);

    theGameBoard = new BorderPane();
    theGameBoard.setCenter(theBoard.getComponent());
    theGameBoard.setBottom(theBottom.getComponent());
    theGameBoard.setRight(theRight.getComponent());
    theGameBoard.setLeft(theLeft.getComponent());
    theGameBoard.setTop(theTop.getTopComponent());

    makeScene();
  }

  private void makeScene() {
    scene = new Scene(theGameBoard, 1280, 800);
    scene.getStylesheets().add(DEFAULT_STYLE);
  }
  /**
   * Return theGameBoard borderpane
   */
  public BorderPane getTheGameBoard() {
    return theGameBoard;
  }


  //FIXME: Hook up thru backend later
  public ArrayList<Integer> rollDice() {
    Random myRandom = new Random();
    int r1 = myRandom.nextInt(6) + 1;
    int r2 = myRandom.nextInt(6) + 1;
    theBoard.movePiece(0, r1 + r2);
    ArrayList<Integer> returned_rolls = new ArrayList<>();
    returned_rolls.add(r1);
    returned_rolls.add(r2);
    return returned_rolls;
  }

  /**
   * Get the scene
   * @return scene
   */
  @Override
  public Scene getScene() {
    return scene;
  }
}
