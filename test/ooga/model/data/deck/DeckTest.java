package ooga.model.data.deck;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import ooga.model.data.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

  Card c1;
  Card c2;
  Card c3;
  List<Card> cardList;

  @BeforeEach
  void resetCards() {
    List<Method> methodList = new ArrayList<>();
    cardList = new ArrayList<Card>();
    c1 = new Card("1", "first", methodList);
    c2 = new Card("2", "second", methodList);
    c3 = new Card("3", "third", methodList);
    cardList.add(c1);
    cardList.add(c2);
    cardList.add(c3);
  }

  @Test
  void testConstructor() {
    Deck myDeck = new Deck("name", cardList);
    assertEquals("name", myDeck.getName());
  }

  @Test
  void testEmpty() {
    
    Deck myDeck = new Deck("name", cardList);
    assertFalse(myDeck.isEmpty());
    for (int i = 0; i < 10; i++) {
      myDeck.drawCard();
    }
    assertFalse(myDeck.isEmpty());

    Deck myDeckEmpty = new Deck("name", new ArrayList<>());
    assertTrue(myDeckEmpty.isEmpty());
  }

  @Test
  void testDrawCard() {
    Deck myDeck = new Deck("name", cardList);

    for (int i = 0; i < 8; i++) {
      Card myCard = myDeck.drawCard();
      assertTrue(myCard == c1 || myCard == c2 || myCard == c3);
    }

  }



}
