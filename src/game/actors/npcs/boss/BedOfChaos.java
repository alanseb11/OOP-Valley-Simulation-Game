package game.actors.npcs.boss;

import game.actors.npcs.types.NPC;
import game.interfaces.Growable;

public class BedOfChaos extends NPC implements Growable {
    private int hp = 1000;
    private final int baseDamage = 25;
    private final double accuracy = 0.75;
