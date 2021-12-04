package ooga.display.screens;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.display.Display;
import ooga.display.DisplayManager;
import ooga.display.ui_tools.LanguageUI;
import ooga.display.ui_tools.UIBuilder;

import java.util.List;
import java.util.ResourceBundle;


/**
 * This class makes a player customizer screen that allows players to customize their appearance in
 * game
 *
 * @author Aaric Han
 * @author Henry Huynh
 */
public class EnterPlayersScreen extends Display {

  private VBox playerMenu;
  private Stage myStage;
  private DisplayManager myDisplayManager;
  private UIBuilder myBuilder;
  private ResourceBundle myLangResource;
  private LanguageUI myLanguageUI;
  private VBox myTextAreaVBox;
  private VBox myColorSelectionVBox;
  private static final String DEFAULT_RESOURCE_PACKAGE =
      Display.class.getPackageName() + ".resources.";
  private static final String STYLE_PACKAGE = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  private static final String DEFAULT_STYLE = STYLE_PACKAGE + "mainmenu.css";
  private static final String PLAYER_CUSTOMIZER = "PlayerCustomizer";
  private static final String SELECT_COLORLABEL = "SelectColorLabel";
  private static final String SELECT_COLOR = "SelectColor";
  private static final String PLAYER_NAME = "PlayerNameLabel";
  private static final String ENTER_NAME = "EnterPlayerName";
  private ArrayList<Color> playerColors = new ArrayList<>();
  private Scene scene;

  /**
   * Default constructor for the Player Customization Screen
   * @param stage
   * @param displayManager
   * @param langResource
   */
  public EnterPlayersScreen(Stage stage, DisplayManager displayManager,
      ResourceBundle langResource) {
    myLangResource = langResource;
    myBuilder = new UIBuilder(langResource);
    myStage = stage;
    myDisplayManager = displayManager;
    playerMenu = new VBox();
    playerMenu.getChildren().add(myBuilder.makeLabel(PLAYER_CUSTOMIZER));
    makePlayerCustomizer();
    makeScene();
  }

  private void makePlayerCustomizer() {
    HBox playerCustomizer = new HBox();
    playerCustomizer.getChildren().addAll(makeTextAreas(), makeColorSelection(), makeRight());
    playerMenu.getChildren().add(playerCustomizer);
  }

  private Node makeRight() {
    VBox result = new VBox();
    result.getChildren().add(myBuilder.makeButton("Continue", e -> myDisplayManager.startGame()));
    result.getChildren().add(myBuilder.makeButton("GotoHome", e -> myDisplayManager.goStartMenu()));
    return result;
  }

  private Node makeTextAreas() {
    myTextAreaVBox = new VBox();
    for (int i = 1; i < 5; i++) {
      myTextAreaVBox.getChildren().add(myBuilder.makeLabel(String.format("%s%d", ENTER_NAME, i)));
      myTextAreaVBox.getChildren()
          .add(myBuilder.makeTextField(String.format("%s%d", ENTER_NAME, i)));
    }
    return myTextAreaVBox;
  }

  private Node makeColorSelection() {
    myColorSelectionVBox = new VBox();
    for (int i = 1; i < 5; i++) {
      myColorSelectionVBox.getChildren()
          .add(myBuilder.makeLabel(String.format("%s", SELECT_COLORLABEL)));
      ComboBox colorSelectorBox = new ComboBox();
      ObservableList<String> options =
          FXCollections.observableArrayList(
              "BLACK", "RED", "GREEN", "BLUE", "YELLOW", "VIOLET"
          );
      colorSelectorBox.setItems(options);
      colorSelectorBox.setId(String.format("%s%d", myLangResource.getString(SELECT_COLOR), i));
      myColorSelectionVBox.getChildren().add(colorSelectorBox);
    }
    return myColorSelectionVBox;
  }

  /**
   * Gets a list of all textfields in the player customization screen
   *
   * @return List of Player Name Customizer text fields
   * @deprecated use {@link #getPlayerNameTextAreaInfo()} instead.
   */
  @Deprecated
  public List<Node> getTextAreaInfo() {
    List<Node> textAreaList = new ArrayList<>();
    textAreaList.add(myTextAreaVBox.getChildren().get(1));
    textAreaList.add(myTextAreaVBox.getChildren().get(3));
    textAreaList.add(myTextAreaVBox.getChildren().get(5));
    textAreaList.add(myTextAreaVBox.getChildren().get(7));
    return textAreaList;
  }

  /**
   * Gets a list of all textfields in the player customization screen
   *
   * @return List of Player Name Customizer text fields
   */
  public List<Node> getPlayerNameTextAreaInfo() {
    List<Node> textAreaList = new ArrayList<>();
    for (Node nodeCheck : myTextAreaVBox.getChildren()) {
      if (nodeCheck.getId() != null && nodeCheck.getId().contains(ENTER_NAME)) {
        textAreaList.add(nodeCheck);
      }
    }
    return textAreaList;
  }

  /**
   * Gets a list of all Color ComboBox in the player customization screen
   *
   * @return List of String colors
   */
  //FIXME: error with getting player colors
  public List<Node> getPlayerColors() {
    List<Node> colorsComboBoxes = new ArrayList<>();
    for (Node nodeCheck : myColorSelectionVBox.getChildren()) {
      if (nodeCheck.getId() != null && nodeCheck.getId().contains(SELECT_COLOR)) {
        colorsComboBoxes.add(nodeCheck);
      }
      else {
        System.out.println("error getting color? see getPlayerColors()");
      }
    }
    return colorsComboBoxes;
  }

  private void makeScene() {
    scene = new Scene(playerMenu, 800, 600);
    scene.getStylesheets().add(DEFAULT_STYLE);
  }

  /**
   * Get the scene
   *
   * @return scene
   */
  @Override
  public Scene getScene() {
    return scene;
  }

}