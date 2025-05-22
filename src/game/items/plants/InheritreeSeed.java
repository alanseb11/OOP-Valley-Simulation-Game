package game.items.plants;

import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import game.grounds.Inheritree;
import game.grounds.Soil;
import game.capabilities.Status;

/**
 * A class representing the {@link InheritreeSeed} item.
 * This seed can be planted to grow an Inheritree.
 */
public class InheritreeSeed extends Plantable {
    /**
     * Constructor.
     */
    public InheritreeSeed() {
        super("Inheritree Seed", '*', true, new Inheritree(), 25);
    }

    /**
     * Plants the Inheritree at the specified location.
     *
     * @param here The location where the seed is planted
     */
    @Override
    public void bloom(Location here) {
        // Bloom the Inheritree here
        super.bloom(here);

        // Cleanse cursed tiles around this location
        for (Exit exit : here.getExits()) {
            Location adjacent = exit.getDestination();
            if (adjacent.getGround().hasCapability(Status.CURSED)) {
                adjacent.setGround(new Soil());  // Replace with soil
            }
        }
    }

}
