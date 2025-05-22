package game.items.plants;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Bloodrose;

/**
 * A class representing the Bloodrose Seed item.
 * This seed can be planted to grow a Bloodrose.
 */
public class BloodroseSeed extends Plantable {
    /**
     * Constructor.
     */
    public BloodroseSeed() {
        super("Bloodrose Seed", '*', true, new Bloodrose(), 75);
    }

    /**
     * This method is called when the seed is planted.
     * It adds the Bloodrose to the map and saps the player's health.
     *
     * @param actor The actor who planted the seed
     * @param map   The game map where the seed is planted
     */
    @Override
    public void plant(Actor actor, GameMap map) {
        super.plant(actor, map);

        // Sap the player's health once planted
        actor.hurt(5);
    }

}
