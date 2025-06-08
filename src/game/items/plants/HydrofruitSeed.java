package game.items.plants;

import game.grounds.plants.HydrofruitSprout;

public class HydrofruitSeed extends Plantable{
    /**
     * Constructor.
     */
    public HydrofruitSeed() {
        super("HydrofruitSeed", '*', true, new HydrofruitSprout(), 50);
    }
}
