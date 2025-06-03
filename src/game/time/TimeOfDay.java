package game.time;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Abstract class representing a time of day in the game.
 * Each time of day has a name and a duration, and can exert effects on the game map.
 */
public abstract class TimeOfDay {
    private String name;
    private Countdown duration;
    
    public TimeOfDay(String name, Countdown duration) {
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean hasPassed() {
        return duration.isExpired();
    }

    public void tick() {
        duration.decrement();
    }

    public void reset() {
        duration.resetCountdown();
    }

    public abstract void applyEffect(Actor actor, GameMap map);

}
