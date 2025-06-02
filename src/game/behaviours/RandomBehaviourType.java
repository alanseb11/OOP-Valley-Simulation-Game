package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * A strategy that randomly selects a behaviour to try.
 * If the selected behaviour is not valid, no other behaviours are tried.
 */
public class RandomBehaviourType implements BehaviourType {
    private final Random random = new Random();

    @Override
    public Action selectAction(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map) {
        if (behaviours.isEmpty()) {
            return null;
        }

        // Convert behaviours to list for random selection
        ArrayList<Behaviour> behaviourList = new ArrayList<>(behaviours.values());
        
        // Select a random behaviour
        Behaviour selectedBehaviour = behaviourList.get(random.nextInt(behaviourList.size()));
        
        // Try only the selected behaviour
        return selectedBehaviour.getAction(actor, map);
    }
} 