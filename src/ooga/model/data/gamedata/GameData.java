package ooga.model.data.gamedata;

import java.util.List;
import ooga.exceptions.NoRemainingPlayersException;
import ooga.model.data.deck.DeckManager;
import ooga.model.die.Die;
import ooga.model.game_handling.board_manager.BoardManager;
import ooga.model.data.player.Player;
import ooga.model.data.player.PlayerManager;

/**
 * A class to store the data of the current game. This includes players and all their associated info, as well as the board_manager and its state.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public class GameData {

    //The current game board_manager.
    private BoardManager myBoard;

    //A class to keep track of the players in the game.
    private PlayerManager myPlayers;

    //A class to keep track of the active decks in the game.
    private DeckManager myDecks;

    //The player whose turn it currently is.
    private Player currentPlayer;

    //The number of rolls this turn.
    private int numRolls;

    //The previous roll of this turn.
    private int previousRoll;

    //The die being used to generate player rolls.
    private Die myDie;

    /**
     * Constructs a new GameData with the specified board, players, and die.
     *
     * @param players the players in the game.
     * @param board the board on which the game is played.
     * @param die the die with which the game is played.
     */
    public GameData(PlayerManager players, BoardManager board, Die die) {
        this.myPlayers = players;
        this.myBoard = board;
        this.myDie = die;
        resetTurnData();
    }

    /**
     * Returns the current board.
     *
     * @return the current board.
     */
    public BoardManager getBoard() {
        return myBoard;
    }

    /**
     * Switches the current player to the next player in the order.
     *
     * @throws NoRemainingPlayersException if no players remain in the game.
     */
    public void setNextPlayer() {
        try {
            this.currentPlayer = myPlayers.getNextPlayer();
        } catch (NoRemainingPlayersException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the data pertaining to the current turn.
     */
    public void resetTurnData() {
        this.numRolls = 0;
        this.previousRoll = -1;
    }

    /**
     * Returns the current player.
     *
     * @return the current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the next player.
     *
     * @return the next player.
     */
    public Player getNextPlayer() {
        Player nextP = null;
        try {
             nextP = myPlayers.getNextPlayer();
        } catch (NoRemainingPlayersException e) {
            e.printStackTrace();
        }
        return nextP;
    }

    /**
     * Returns the number of rolls this turn.
     *
     * @return the number of rolls this turn.
     */
    public int getNumRolls() {
        return numRolls;
    }

    /**
     * Adds to the total number of rolls.
     */
    public void addRoll() {
        this.numRolls++;
    }

    /**
     * Returns the previous roll this turn.
     *
     * @return the previous roll this turn.
     */
    public int getPreviousRoll() {
        return previousRoll;
    }

    /**
     * Returns the current die state.
     *
     * @return the current die state.
     */
    public Die getDie() {
        return myDie;
    }

    /**
     * Returns the game's active decks.
     *
     * @return the game's active decks.
     */
    public DeckManager getDecks() {
        return myDecks;
    }

    public List<Player> getPlayers() {
        return myPlayers.getPlayers();
    }
}