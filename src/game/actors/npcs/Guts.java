package game.actors.npcs;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.npcs.types.MonologuingNPC;
import game.behaviours.AttackBehaviour;
import game.capabilities.Threshold;
import game.monologueconditions.ConditionalMonologue;
import game.monologueconditions.DefaultCondition;
import game.monologueconditions.HealthCondition;
import game.weapons.BareFist;

/**
 * Class representing the Guts NPC.
 */
public class Guts extends MonologuingNPC {

    /**
     * Constructor.
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.setIntrinsicWeapon(new BareFist());

        // Register behaviours
        behaviours.put(0, new AttackBehaviour(target -> target.getAttribute(BaseActorAttributes.HEALTH) > 50));

        // Initialise monologue pool
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "RAAAAGH!"));
        monologuePool.add(new ConditionalMonologue(new DefaultCondition(), "I'LL CRUSH YOU ALL!"));
        monologuePool.add(new ConditionalMonologue(new HealthCondition(50, Threshold.BELOW), "WEAK! TOO WEAK TO FIGHT ME!"));
    }

}
