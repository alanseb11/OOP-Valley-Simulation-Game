package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;
import java.util.TreeMap;

/**
 * A behavior selection type that implements a priority-based selection mechanism.
 * This class tries behaviors in order of their priority (lowest number first) until it finds
 * a valid action that can be performed.
 * 
 * For example, if an NPC has these behaviors with priorities:
 * - Priority 0: Produce offspring
 * - Priority 1: Follow target
 * - Priority 99: Wander around
 * 
 * The NPC will:
 * 1. First try to produce offspring
 * 2. If that's not possible, try to follow a target
 * 3. If that's not possible, try to wander around
 * 4. If none are possible, return null (leading to a DoNothingAction)
 * 
 * This type is useful for NPCs that need to maintain a strict order of behavior preferences,
 * ensuring more important behaviors are always attempted before less important ones.
 * 
 * @see BehaviourType
 * @see Behaviour
 */
public class PrioritisedBehaviourType implements BehaviourType {
    
    /**
     * Selects an action by trying behaviors in priority order until a valid one is found.
     * Uses a TreeMap to ensure behaviors are sorted by their priority keys.
     * 
     * @param behaviours A map of behaviors with their priorities that the actor can perform
     * @param actor The actor that will perform the selected behavior
     * @param map The current game map where the action will be performed
     * @return The first valid action found when trying behaviors in priority order, or null if no valid action is found
     */
    @Override
    public Action selectAction(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map) {
        // Use TreeMap to ensure behaviors are sorted by priority (lowest to highest)
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