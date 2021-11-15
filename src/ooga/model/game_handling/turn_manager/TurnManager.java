package ooga.model.game_handling.turn_manager;

import ooga.display.communication.DisplayComm;
import ooga.display.communication.ExceptionHandler;
import ooga.exceptions.MaxRollsReachedException;
import ooga.model.data.gamedata.GameData;
import ooga.model.data.player.Player;
import ooga.model.data.tiles.Tile;
import ooga.model.die.Die;
import ooga.model.game_handling.FunctionExecutor;

/**
 * This class manages the current turn and keeps track of all turn-based data.
 * 
 * @author William Convertino
 * 
 * @since 0.0.1
 */
public class TurnManager {

    private GameData gameData;

    private Tile selectedTile;

    private int maxRolls;

    private FunctionExecutor functionExecutor;

    private DisplayComm displayComm;

    private boolean commandActive;

    /**
     * Default constructor
     */
    public TurnManager(GameData gameData, FunctionExecutor functionExecutor, DisplayComm displayComm) {
        this.gameData = gameData;
        this.functionExecutor = functionExecutor;
        this.displayComm = displayComm;
        this.maxRolls = 1;
        this.commandActive = true;
    }

    /**
     * Starts the next turn.
     */
    public void startTurn() {
        this.selectedTile = null;
        gameData.resetTurnData();
        gameData.setNextPlayer();
    }

    /**
     * Makes the player roll the dice, and move accordingly. If they roll doubles, they are allowed to roll an additional time.
     * If they roll 3 times, they are sent to jail.
     */
    public void roll() {

        //Check to see if the player has already rolled the max # of times, if so throw an error.
        if (gameData.getNumRolls() >= maxRolls) {
            displayComm.showException(new MaxRollsReachedException());
            return;
        }

        //Roll the die.
        int myRoll = gameData.getDie().roll();
        gameData.addRoll();

        //If the roll is the third of the turn, send the player to jail.
        if (gameData.getNumRolls() > 3) {
            //TODO: Go to jail.
            return;
        }

        //If the roll is a double, increase the maximum number of rolls by one.
        if (gameData.getDie().lastRollDouble()) {
            maxRolls++;
        }
        functionExecutor.movePlayerFd(gameData.getCurrentPlayer(), myRoll);
    }


}