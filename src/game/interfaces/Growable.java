package game.interfaces;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing an entity capable of growth, typically in the context of game entities that
 * can expand or transform during gameplay (e.g., bosses, plants).
 *
 * <p>
 * Provides a contract for growth actions, defining the growth logic and the conditions under which
 * growth is permitted.
 * </p>
 *
 * <p>
 * Implementing classes should define how they grow and the rules that determine if growth can occur
 * (e.g., proximity of players or map conditions).
 * </p>
 */
public interface Growable {

    /**
     * Executes the growth logic of the implementing class.
     *
     * <p>
     * This could involve adding new parts, transforming state, or performing other growth-related
     * actions. The returned String provides a description of the growth event.
     * </p>
     *
     * @return A textual description of the growth result.
     */
    String grow();

    /**
     * Determines whether the implementing class is able to grow based on its current state
     * and the environment (GameMap).
     *
     * @param map The GameMap that contains the entity.
     * @return true if the entity can grow; false otherwise.
     */
    boolean canGrow(GameMap map);
}
