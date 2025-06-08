package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Countdown;
import game.actions.CureAction;
import game.capabilities.Status;
import game.interfaces.Sprout;

public class HydrofruitSprout extends Ground implements Sprout {
    private final Countdown timeUntilGrown = new Countdown(7);
    private int watered = 0;

    /**
     * Constructor.
     */
    public HydrofruitSprout() {
        super('h', "HydrofruitSprout");
        this.addCapability(Status.PLANTED);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Check if the actor is on the same location as the blight
        if (location.getActor() == actor) {
//            // Allow curing if the other actor has a curative item
//            for (Item item : actor.getItemInventory()) {
//                if (item.hasCapability(Status.CURATIVE)) {
//                    actions.add(new CureAction(item, this));
//                }
//            }
        }

        return actions;
    }

    @Override
    public void tick(Location location) {
        if (timeUntilGrown.isExpired() && !location.containsAnActor()) {
            grow(location);
        } else {
            timeUntilGrown.applyTo(this, "growing into a Hydrofruit");
        }
    }

    @Override
    public void grow(Location location) {
        return;
    }
}
