package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;
import java.util.TreeMap;

/**
 * A strategy that selects behaviors based on their priority.
 * Higher priority behaviors (lower numbers) are tried first.
 */
public class PrioritisedBehaviourType implements BehaviourType {
    @Override
    public Action selectAction(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map) {
        // Use TreeMap to ensure behaviors are sorted by priority
        TreeMap<Integer, Behaviour> sortedBehaviours = new TreeMap<>(behaviours);
        
        // Try each behavior in order of priority
        for (Behaviour behaviour : sortedBehaviours.values()) {
            Action action = behaviour.getAction(actor, map);
            if (action != null) {
                return action;
            }
        }
        return null;
    }
} 