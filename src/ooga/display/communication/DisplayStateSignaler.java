package ooga.display.communication;

import ooga.display.DisplayManager;

/**
 * Class that signals to the display when states happen in the back end
 *
 * @author William Convertino
 * @author Aaric Han
 * @author Henry Huynh
 */
public class DisplayStateSignaler {

  DisplayManager myDisplayManager;

  public enum State {

    //Display prompts.
    PLAYER_WIN,
    GO_TO_JAIL,

    //Button Prompts
    READY_TO_ROLL

  }

  /**
   * Constructor for the DisplayStateSignaler which stores a display manager
   * @param displayManager
   */
  public DisplayStateSignaler (DisplayManager displayManager) {
    myDisplayManager = displayManager;
  }

  /**
   * Signal Display with a state
   * @param s
   */
  public void signalDisplay (State s) {
    // ex: if s = buy property show buy prop button
    // myDisplayManager.signalState(s)
  }
}
