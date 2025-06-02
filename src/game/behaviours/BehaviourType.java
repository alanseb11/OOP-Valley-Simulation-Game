package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;

/**
 * An interface that defines different types of behavior selection mechanisms for NPCs.
 * This interface follows the Strategy pattern to allow different algorithms for selecting
 * which behavior an NPC should perform on their turn.
 * 
 * There are two main types of behavior selection:
 * 1. Prioritised - where behaviors are tried in order of priority until a valid one is found
 * 2. Random - where a single random behavior is selected and tried
 * 
 * This design allows for:
 * - Easy addition of new behavior selection types
 * - Runtime switching between different behavior types
 * - Clear separation between behavior implementation and behavior selection
 * 
 * @see PrioritisedBehaviourType
 * @see RandomBehaviourType
 * @see Behaviour
 */
public interface BehaviourType {
    /**
     * Selects and returns an action based on the available behaviors using this type's selection algorithm.
     * 
     * @param behaviours A map of behaviors with their priorities that the actor can perform
     * @param actor The actor that will perform the selected behavior
     * @param map The current game map where the action will be performed
     * @return An Action that can be performed by the actor, or null if no valid action is found
     */
    Action selectAction(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map);
} 