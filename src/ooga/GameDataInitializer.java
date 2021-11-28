package ooga;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.ImproperlyFormattedFile;
import ooga.model.data.cards.Card;
import ooga.model.data.deck.Deck;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.OriginalPlayerManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.CardTileModel;
import ooga.model.data.tilemodels.EmptyTileModel;
import ooga.model.data.tilemodels.PropertyTileModel;
import ooga.model.data.tilemodels.TileModel;
import ooga.model.die.Die;
import ooga.model.die.OriginalDice;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.game_handling.board_manager.OriginalBoardManager;
import ooga.model.game_handling.commands.ActionSequenceParser;
import ooga.model.game_handling.parsers.BoardParser;
import ooga.model.game_handling.parsers.CardParser;
import ooga.model.game_handling.parsers.PlayerParser;
import ooga.model.game_handling.parsers.PropertyParser;
import ooga.model.game_handling.parsers.TileParser;

/**
 * This class parses all configuration files and preps program for game.
 *
 * @author Casey Goldstein
 * @author William Convertino
 * @author Jordan Castleman
 *
 * @since 0.0.1
 */
public class GameDataInitializer {

  //TODO: Replace these with a config file
  public static final String PLAYER_NAMES = "/players/players.info";
  public static final String STARTING_VALUES = "/players/starting_values.info";
  public static final String PROPERTIES = "/properties";
  public static final String TILES = "/board/tiles";
  public static final String CHANCE = "/cards/chance";
  public static final String COMMUNITY_CHEST = "/cards/community_chest";
  public static final String BOARD = "/board/";
  public static final String BOARD_SUFFIX = ".board";
  public static final String CONFIG = "/config";
  public static final String DATA_PATH = "data/";

  public static final String PLAYER_MANAGER = "PlayerManager";
  public static final String BOARD_MANAGER = "BoardManager";
  public static final String DIE = "Die";

  public static PropertyParser propertyParser;
  public static CardParser cardParser;
  public static TileParser tileParser;
  public static ActionSequenceParser actionSequenceParser;

  public static Object playerManager;
  public static Deck chanceDeck;
  public static Deck communityChestDeck;
  private ArrayList<Player> playerList;
  private OriginalDice dice;
  private GameData gameData;
  private ArrayList<Property> propertyList;


  public static GameData generateGameData(String variationFilePath)
      throws ImproperlyFormattedFile, AttributeNotFoundException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {



    GameData data = new GameData();
    FunctionExecutor executor = new FunctionExecutor();

    ResourceBundle modelConfig = ResourceBundle.getBundle(variationFilePath + CONFIG);//).replaceAll("/", ".") );

    String currentFile = null;

    try {
      currentFile = PLAYER_NAMES;
      List<Player> myPlayers = PlayerParser.getPlayersFromFile(DATA_PATH + variationFilePath + PLAYER_NAMES);

      currentFile = CONFIG;

      playerManager = Class.forName(modelConfig.getString(PLAYER_MANAGER)).getConstructor(List.class).newInstance(myPlayers);

      propertyParser = new PropertyParser();
      actionSequenceParser = new ActionSequenceParser(executor,data);
      cardParser = new CardParser(actionSequenceParser);

      currentFile = CHANCE;
      chanceDeck = new Deck("Chance",cardParser.parseCards(variationFilePath + currentFile));
      //monopoly_original/players/players.info
      currentFile = COMMUNITY_CHEST;
      communityChestDeck = new Deck ("Community Chest",cardParser.parseCards(variationFilePath + currentFile));

      List<Deck> deckList = new ArrayList<>();
      deckList.add(chanceDeck);
      deckList.add(communityChestDeck);

      data.setDeckManager(deckList);

      tileParser = new TileParser(actionSequenceParser,data);



      //TODO: Integrate property parser
      currentFile = PROPERTIES;

      List<Property> myPropertyList = propertyParser.parseProperties(variationFilePath + currentFile);

      /*
      for (Property prop: myPropertyList){
        System.out.println(prop.getName());
      }

       */

      currentFile = TILES;
      Map<String,PropertyTileModel> propertyTileList = tileParser.parsePropertyTiles(myPropertyList);
      Map<String,TileModel> nonPropertyTileList = tileParser.parseNonPropertyTiles(variationFilePath + currentFile);

      Map<String,TileModel> tileModelMap = new HashMap<>();
      tileModelMap.putAll(propertyTileList);
      tileModelMap.putAll(nonPropertyTileList);


      currentFile = BOARD;
      BoardParser myBoardParser = new BoardParser();
      List<TileModel> myTiles = myBoardParser.parseBoard(DATA_PATH + variationFilePath + currentFile + variationFilePath + BOARD_SUFFIX,tileModelMap);

      System.out.println(myTiles.size());

      //FOR TESTING TODO: Remove and replace with parsing.
      //myTiles.add(new EmptyTileModel("t1"));
      //myTiles.add(new EmptyTileModel("t2"));
      //myTiles.add(new EmptyTileModel("t3"));

      //BoardManager myBoardManager = (BoardManager) Class.forName(modelConfig.getString(BOARD_MANAGER)).getConstructor(List.class).newInstance(myTiles);
      //Die myDie = (Die) Class.forName(modelConfig.getString(DIE)).getConstructor().newInstance();

      //GameData myGameData = new GameData(myPlayerManager, myBoardManager, myDie);
      return null;

    } catch (Exception e) {
      e.printStackTrace();
      e.getCause();
      throw new ImproperlyFormattedFile(variationFilePath + currentFile);
    }
  }


}
