package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Countdown;
import game.Hatch;
import game.actors.npcs.OmenSheep;
import game.interfaces.Eatable;

public class Egg extends Item implements Eatable {
    private final Countdown timeUntilHatch = new Countdown(3);
    private final OmenSheep hatched = new OmenSheep();
    private final Hatch hatch = new Hatch(this);

    /***
     * Constructor.
     */
    public Egg() {
        super("Egg", '0', true);
    }

    @Override
    public void tick(Location currentLocation) {
        if (canHatch(hatch)) {
            String execute = hatch.execute(currentLocation, hatched);
            new Display().println(execute);
        }
    }

    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        if (actor.getItemInventory().contains(this)) {
            timeUntilHatch.resetCountdown();
        }
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        return getEatAction(owner);
    }

    @Override
    public String eatenBy(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,10);
        actor.removeItemFromInventory(this);
        return actor + " consumes the Egg and restores 10 health!";
    }

    public boolean canHatch(Hatch hatch) {
        if (timeUntilHatch.isExpired()) {
            return true;
        }
        String effect = hatch.hatchDescription(hatched);
        timeUntilHatch.applyToItem(this, effect);
        return false;
    }
}
