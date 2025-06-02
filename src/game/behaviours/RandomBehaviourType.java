package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * A behavior selection type that implements a random selection mechanism.
 * This class randomly selects one behavior from all available behaviors and tries to perform it.
 * If the selected behavior cannot be performed, no other behaviors are tried.
 * 
 * For example, if an NPC has these behaviors:
 * - Produce offspring
 * - Follow target
 * - Wander around
 * 
 * The NPC will:
 * 1. Randomly select one of these behaviors (equal probability)
 * 2. Try to perform the selected behavior
 * 3. If the behavior cannot be performed, do nothing
 * 4. Will NOT try other behaviors if the selected one fails
 * 
 * This type is useful for NPCs that should exhibit more unpredictable behavior,
 * making them less deterministic and potentially more interesting or challenging
 * to interact with.
 *
 * @see BehaviourType
 * @see Behaviour
 */
public class RandomBehaviourType implements BehaviourType {
    /**
     * Random number generator for selecting behaviors.
     */
    private final Random random = new Random();

    /**
     * Selects an action by randomly choosing one behavior and attempting to perform it.
     * Unlike the prioritised type, this will only try one behavior and return null if
     * that behavior cannot be performed.
     * 
     * @param behaviours A map of behaviors with their priorities that the actor can perform
     * @param actor The actor that will perform the selected behavior
     * @param map The current game map where the action will be performed
     * @return The action from the randomly selected behavior if valid, or null if the behavior cannot be performed
     */
    @Override
    public Action selectAction(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map) {
        if (behaviours.isEmpty()) {
            return null;
        }

        // Convert behaviors to list for random selection
        ArrayList<Behaviour> behaviourList = new ArrayList<>(behaviours.values());
        
        // Select a random behavior
        Behaviour selectedBehaviour = behaviourList.get(random.nextInt(behaviourList.size()));
        
        // Try only the selected behavior
        return selectedBehaviour.getAction(actor, map);
    }
} 