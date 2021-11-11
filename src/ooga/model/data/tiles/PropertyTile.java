package ooga.model.data.tiles;

import ooga.model.data.collectables.properties.Property;
import ooga.model.data.player.Player;
import ooga.model.game_handling.GameFunctions;

/**
 * This class represents a tile corresponding to a property card. When a player lands on it, they may buy it if it is unowned, or pay rent for it if it is owned by another player.
 */
public abstract class PropertyTile extends Tile {

    /**
     * Default constructor
     */
    public PropertyTile() {
    }

    /**
     * 
     */
    protected Property property;

    /**
     * @param player 
     * @param functionManager
     */
    public abstract void executePassThrough(Player player, GameFunctions functionManager);

    /**
     * @param player 
     * @param functionManager
     */
    public abstract void executeLandOn(Player player, GameFunctions functionManager);

}