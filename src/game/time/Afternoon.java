package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Ability;
import game.capabilities.Status;

public class Afternoon extends TimeOfDay {
    /**
     * Constructor for Afternoon time of day.
     * Initialises the afternoon with a duration of 1 turn.
     */
    public Afternoon() {
        super("Afternoon", new Countdown(1));
    }

    @Override
    public void applyEffect(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actor.addCapability(Ability.PURCHASE);
        }
    }

}
