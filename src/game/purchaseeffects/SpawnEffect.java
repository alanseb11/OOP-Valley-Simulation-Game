package game.purchaseeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import game.interfaces.PurchaseEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpawnEffect implements PurchaseEffect {
    private Actor actorToSpawn;
    private Actor anchorActor; // The actor to spawn near

    /**
     * Constructor.
     * @param actorToSpawn The actor to be spawned.
     * @param anchorActor The actor around which the new actor will be spawned.
     */
    public SpawnEffect(Actor actorToSpawn, Actor anchorActor) {
        this.actorToSpawn = actorToSpawn;
        this.anchorActor = anchorActor;
    }

    /**
     * Spawns the actorToSpawn on a random available adjacent tile to the anchorActor.
     * @param buyer The actor making the purchase.
     * @param map The GameMap where spawning occurs.
     * @return A message describing the spawn, or an error if no location is found.
     */
    @Override
    public String uponPurchase(Actor buyer, GameMap map) {
        if (this.anchorActor == null) {
            return "Anchor actor for spawning is null.";
        }
        if (this.actorToSpawn == null) {
            return "Actor to spawn is null.";
        }
        
        Location anchorLocation = map.locationOf(anchorActor);
        if (anchorLocation == null) {
            return anchorActor.toString() + " not found on map, cannot spawn " + actorToSpawn.toString() + ".";
        }

        List<Location> possibleLocations = new ArrayList<>();
        for (Exit exit : anchorLocation.getExits()) {
            Location destination = exit.getDestination();
            // Check if destination is valid, actor can enter, and no other actor is there
            if (destination != null && map.isAnActorAt(destination) == false && destination.canActorEnter(actorToSpawn)) {
                possibleLocations.add(destination);
            }
        }

        if (possibleLocations.isEmpty()) {
            return "No available adjacent spawn location found near " + anchorActor.toString() + " for " + actorToSpawn.toString() + ".";
        }

        Collections.shuffle(possibleLocations);
        Location spawnLocation = possibleLocations.get(0);
        map.addActor(this.actorToSpawn, spawnLocation);
        
        return actorToSpawn.toString() + " has spawned near " + anchorActor.toString() + ".";
    }
}
