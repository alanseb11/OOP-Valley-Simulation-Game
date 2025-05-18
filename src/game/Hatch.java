package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Egg;

public class Hatch {
    private Egg egg;

    public Hatch(Egg egg) { this.egg = egg; }

    public String execute(Location location, Actor hatched) {
        location.removeItem(egg);
        location.addActor(hatched);
        return hatchDescription(hatched);
    }

    public String hatchDescription(Actor hatched) {
        String article = Utility.startsWithVowel(hatched.toString()) ? "an " : "a ";
        return egg + " hatches into " + article + hatched;
    }
}
