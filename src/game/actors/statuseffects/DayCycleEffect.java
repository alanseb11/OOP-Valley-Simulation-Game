package game.actors.statuseffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Daybound;

public class DayCycleEffect extends StatusEffect {
    private final Daybound Daybound;
    
    public DayCycleEffect(Daybound Daybound) {
        super("Day cycle's effect");
        this.Daybound = Daybound;
    }

    @Override
    public void tick(Location location, Actor actor) {
        Daybound.getTimeManager().tick(actor);
    }

}
