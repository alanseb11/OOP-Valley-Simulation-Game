package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;

public class EatBeetleAction extends Action {

    public final Actor beetle;
    public EatBeetleAction(Actor beetle){
        this.beetle = beetle;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(15);

        if (actor.hasCapability(Status.PLAYER)) {
            actor.addBalance(1000);
        }

        map.removeActor(beetle);

        return actor + " eats the " + beetle + " and gains 15 health + 1000 runes!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats the " + beetle;
    }
}
