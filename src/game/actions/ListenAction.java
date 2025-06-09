package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.types.MonologuingNPC;

/**
 * Represents an action where an {@link Actor} listens to a {@link MonologuingNPC}.
 * This action allows the actor to hear the monologue of the specified monologuer.
 */
public class ListenAction extends Action {
    private MonologuingNPC monologuer;

    /**
     * Constructor.
     *
     * @param monologuer The monologue to be spoken
     */
    public ListenAction(MonologuingNPC monologuer) {
        this.monologuer = monologuer;
    }

    /**
     * Executes the action.
     *
     * @param actor The actor performing the action
     * @param map   The map containing the actor
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return monologuer + ": \"" + monologuer.playMonologue(actor, map) + "\"";
    }

    /**
     * Returns a string describing the action.
     *
     * @param actor The actor performing the action
     * @return A string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to " + monologuer;
    }

}
