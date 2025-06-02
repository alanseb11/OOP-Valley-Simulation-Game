package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An Action that moves an Actor from one map location to another, potentially across different maps.
 * This action is used for teleportation mechanics in the game, allowing actors to instantly
 * move between different locations or maps.
 * 
 * The teleportation process involves:
 * 1. Removing the actor from their current location
 * 2. Adding them to the destination location
 * 3. Handling the transition between different maps if necessary
 * 
 * This action is typically used in conjunction with TeleportationCircle ground type
 * to create a network of teleportation points in the game world.
 */
public class TeleportAction extends Action {
    
    /**
     * The destination location where the actor will be teleported to.
     * This can be on the same map or a different map.
     */
    private final Location destination;

    /**
     * Creates a new TeleportAction with the specified destination.
     *
     * @param destination The location where the actor will be teleported to
     */
    public TeleportAction(Location destination) {
        this.destination = destination;
    }

    /**
     * Executes the teleport action, moving the actor to the destination location.
     * Handles the removal from the current map and addition to the destination map.
     *
     * @param actor The actor performing the teleport
     * @param map The map the actor is currently on
     * @return A description of the teleportation action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Remove actor from current map
        map.removeActor(actor);
        
        // Add actor to the destination map at the specified location
        destination.map().addActor(actor, destination);
        
        return actor + " teleports to " + destination.map().toString();
    }

    /**
     * Returns a description of this action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action
     * @return A string describing the teleport action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + destination.map().toString();
    }
}
