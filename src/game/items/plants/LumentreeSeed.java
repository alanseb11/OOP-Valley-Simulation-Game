package game.items.plants;

import game.grounds.plants.LumentreeSprout;

public class LumentreeSeed extends Plantable{
    /**
     * Constructor.
     */
    public LumentreeSeed() {
        super("LumentreeSeed", '*', true, new LumentreeSprout(), 50);
    }
}
