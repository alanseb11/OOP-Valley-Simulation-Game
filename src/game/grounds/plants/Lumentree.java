package game.grounds.plants;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;
import game.interfaces.Daybound;
import game.items.food.Lumenfruit;
import game.time.*;

public class Lumentree extends Ground implements Daybound {
    private final Countdown timeUntilProduce = new Countdown(5);
    private final TimeManager timeManager = new TimeManager();

    /**
     * Constructor.
     */
    public Lumentree() {
        super('L', "Lumentree");
        this.addCapability(Status.PLANTED);

        timeManager.add(new Morning());
        timeManager.add(new Afternoon());
        timeManager.add(new Night());
    }

    @Override
    public void tick(Location location) {
        // Applies the tick cycle of the time manager
        this.getTimeManager().tick();
        // Only produces fruit when it's nighttime
        if (this.getTimeManager().getCurrentTime().hasCapability(Status.NIGHT)) {
            // Checks if the countdown for growth is expired
            if (timeUntilProduce.isExpired()) {
                location.addItem(new Lumenfruit());
                timeUntilProduce.resetCountdown();
            } else {
                timeUntilProduce.applyTo(this, "producing a Lumenfruit");
            }
        }
    }

    @Override
    public TimeManager getTimeManager() {
        return timeManager;
    }
}
