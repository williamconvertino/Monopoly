package ooga.model.data.player;

import java.util.ArrayList;
import java.util.List;
import ooga.model.data.cards.Card;
import ooga.model.data.properties.Property;
import ooga.util.ImmutTool;

/**
 * A class to keep track of a single player's data.
 *
 * @author William Convertino
 *
 * @since 0.0.1
 */
public class Player {

    //The name of the player.
    private String myName;

    //Keeps track of whether the player is actively in the game.
    private boolean isActive;

    //Keeps track of whether the player is in jail or not.
    private boolean isInJail;

    //Keeps track of the player's cash balance.
    private int balance;

    //Keeps track of the player's location on the board_manager.
    private int location;

    //A list of the player's properties.
    private List<Property> myProperties;

    //A list of the player's cards.
    private List<Card> myCards;

    /**
     * Constructs a new Player with the given name.
     *
     * @param name the player's name.
     */
    public Player(String name) {
        this.myName = name;
        this.isActive = true;
        this.isInJail = false;
        this.location = 0;
        this.balance = 0;
        this.myProperties = new ArrayList<>();
        this.myCards = new ArrayList<>();
    }

    /**
     * Returns the player's name.
     *
     * @return the player's name.
     */
    public String getName() {
        return myName;
    }

    /**
     * States whether the player is actively in the game or not.
     *
     * @return true if the player is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the player's active state to the specified state.
     *
     * @param isActive states whether the player should be active or not.
     */
    public void setActiveStatus(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * States whether or not the player is in jail.
     *
     * @return true if the player is in jail, and false otherwise.
     */
    public boolean isInJail() {
        return isInJail;
    }

    /**
     * Sets the player's jail status.
     *
     * @param inJail the value to which the player's jail status should be set.
     */
    public void setJailStatus(boolean inJail) {
        this.isInJail = inJail;
    }

    /**
     * Sets the player's location to the number specified.
     *
     * @param location the location at which the player should be set.
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Returns the player's current location.
     *
     * @return the player's current location.
     */
    public int getLocation() {
        return location;
    }

    /**
     *  Returns a list of the player's properties.
     *
     * @return a list of the player's properties.
     */
    public List<Property> getProperties() {
        return ImmutTool.getImmutableList(myProperties);
    }



    /**
     * Adds the specified property to the player's properties.
     *
     * @param property the property to give.
     */
    public void giveProperty(Property property) {
        myProperties.add(property);
    }

    /**
     * Removes the specified property from the player's properties.
     *
     * @param property the property to remove.
     */
    public void removeProperty(Property property) {
        myProperties.remove(property);
    }

    /**
     *  Returns a list of the player's cards.
     *
     * @return a list of the player's cards.
     */
    public List<Card> getCards() {
        return ImmutTool.getImmutableList(myCards);
    }

    /**
     * Adds the specified card to the player's cards.
     *
     * @param card the card to give.
     */
    public void giveCard(Card card) {
        myCards.add(card);
    }

    /**
     * Removes the specified card from the player's cards.
     *
     * @param card the card to remove.
     */
    public void removeCard(Card card) {
        myCards.remove(card);
    }

}