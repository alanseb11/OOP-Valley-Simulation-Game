package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;

/**
 * A special type of ground that allows actors to teleport between different locations or maps.
 * TeleportationCircle creates a network of connected points in the game world that actors can use
 * to quickly move between distant locations.
 * 
 * Each TeleportationCircle has:
 * - A current location and map where it exists
 * - A destination location and map where it will transport actors to
 * - The ability to offer a TeleportAction to actors standing on it
 * 
 * The teleportation network is bidirectional, typically requiring two circles to be set up:
 * - One circle at the source location
 * - Another circle at the destination location
 * Each circle should be configured to teleport to the other's location.
 */
public class TeleportationCircle extends Ground {

    /**
     * The location where this teleportation circle is placed
     */
    private Location location;

    /**
     * The map containing this teleportation circle
     */
    private GameMap currentMap;

    /**
     * The map that this circle teleports to
     */
    private GameMap destinationMap;

    /**
     * The specific location on the destination map that actors will be teleported to
     */
    private Location destinationLocation;

    /**
     * Creates a new TeleportationCircle.
     *
     * @param displayChar character to display for this type of terrain (unused, always 'A')
     * @param name the name of the circle (unused, always "Teleportation Circle")
     */
    public TeleportationCircle(char displayChar, String name) {
        super('A', "Teleportation Circle");
    }

    /**
     * Sets the location and map of this teleportation circle.
     * This should be called when the circle is placed on the map.
     *
     * @param location the location where this circle is placed
     */
    public void setLocation(Location location) {
        this.location = location;
        this.currentMap = location.map();
    }

    /**
     * Sets the destination for this teleportation circle.
     * This should be called when setting up the teleportation network.
     *
     * @param destinationMap the map to teleport to
     * @param destinationLocation the location on the destination map to teleport to
     */
    public void setDestination(GameMap destinationMap, Location destinationLocation) {
        this.destinationMap = destinationMap;
        this.destinationLocation = destinationLocation;
    }

    /**
     * Gets the location of this teleportation circle.
     *
     * @return the location of this circle
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Gets the map this teleportation circle is on.
     *
     * @return the map containing this circle
     */
    public GameMap getCurrentMap() {
        return this.currentMap;
    }

    /**
     * Gets the destination map for this teleportation circle.
     *
     * @return the map to teleport to
     */
    public GameMap getDestinationMap() {
        return this.destinationMap;
    }

    /**
     * Gets the destination location for this teleportation circle.
     *
     * @return the location to teleport to
     */
    public Location getDestinationLocation() {
        return this.destinationLocation;
    }

    /**
     * Returns a list of actions available to an actor standing on this circle.
     * If the actor is on the circle and a valid destination has been set,
     * they will be offered a TeleportAction to move to the destination.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        // Check if the actor is on the same location as the teleport circle
        if (location.getActor() == actor && destinationMap != null && destinationLocation != null) {
            // Add teleport action
            actions.add(new TeleportAction(destinationLocation));
        }
        return actions;
    }
}
