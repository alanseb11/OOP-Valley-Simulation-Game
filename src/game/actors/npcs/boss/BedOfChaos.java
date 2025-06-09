package game.actors.npcs.boss;

import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.GrowBehaviour;
import game.capabilities.Status;
import game.interfaces.Growable;
import game.weapons.BedOfChaosIntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.actions.AttackAction;


import java.util.*;
import java.util.function.Predicate;

public class BedOfChaos extends Actor implements Growable {
    private final int baseDamage = 25;
    private List<BossPart> parts = new ArrayList<>();
    private final Random random = new Random();
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();

    public BedOfChaos() {
        super("Bed of Chaos", 'T', 1000);

        setIntrinsicWeapon(new BedOfChaosIntrinsicWeapon(this));

        Predicate<Actor> isPlayer = actor -> actor.hasCapability(Status.HOSTILE_TO_ENEMY);
        behaviours.put(10, new AttackBehaviour(isPlayer));
        behaviours.put(20, new GrowBehaviour(this));

    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getTotalAttackDamage() {
        int totalDamage = 0;
        for (BossPart part : parts) {
            totalDamage += part.getAttackDamage();
        }
        return totalDamage;
    }

    @Override
    public String grow() {
        if (random.nextBoolean()) {
            Branch branch = new Branch();
            parts.add(branch);
            branch.grow(this);
            return this + " grew a new Branch!";
        } else {
            parts.add(new Leaf());
            this.heal(5);
            return this + " grew a new Leaf!";
        }
    }

    @Override
    public boolean canGrow(GameMap map) {
        // Get the map containing BedOfChaos
        for (Exit exit : map.locationOf(this).getExits()) {
            Actor target = exit.getDestination().getActor();
            if (target != null && target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("Bed of Chaos [HP: %d, Parts: %d, Damage: %d]", this.getAttribute(BaseActorAttributes.HEALTH) , parts.size(), getBaseDamage() + getTotalAttackDamage());
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (edu.monash.fit2099.engine.actors.Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }
}
