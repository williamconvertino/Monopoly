package ooga.util.parsers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import ooga.exceptions.AttributeNotFoundException;
import ooga.exceptions.InvalidFileFormatException;
import ooga.model.data.deck.Deck;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.properties.Property;
import ooga.model.game_handling.FunctionExecutor;
import ooga.model.game_handling.commands.ActionSequenceParser;
import org.junit.jupiter.api.BeforeEach;

public class ParserTest {

  public ArrayList<Property> propertyList;
  public PropertyParser propertyParser;
  public CardParser cardParser;
  public TileParser tileParser;
  public BoardParser boardParser;
  public ActionSequenceParser actionSequenceParser;
  public GameData gameData;


  @BeforeEach
  void initGamestate()
      throws AttributeNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InvalidFileFormatException {
    propertyParser = new PropertyParser();
    propertyList = new ArrayList<>();
    propertyList = propertyParser.parseProperties("variations/monopoly_original/properties");
    gameData = new GameData();
    FunctionExecutor functionExecutor = new FunctionExecutor();
    //create parsers
    actionSequenceParser = new ActionSequenceParser(functionExecutor,gameData);
    cardParser = new CardParser(actionSequenceParser);
    tileParser = new TileParser(actionSequenceParser,gameData);
    boardParser = new BoardParser();


    Deck chanceDeck = new Deck("Chance",cardParser.parseCards(
        "variations/monopoly_original/cards/chance"));
    Deck communityChestDeck = new Deck ("Community Chest",cardParser.parseCards(
        "variations/monopoly_original/cards/community_chest"));

    //combine decks into list, and give to gameData
    List<Deck> deckList = new ArrayList<>();
    deckList.add(chanceDeck);
    deckList.add(communityChestDeck);
    gameData.setDeckManager(deckList);

  }
}