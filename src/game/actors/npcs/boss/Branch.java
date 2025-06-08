package game.actors.npcs.boss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.interfaces.Growable;


public class Branch extends BossPart {
    private final List<BossPart> subParts = new ArrayList<>();
    private final Random random = new Random();

    public Branch() {
        super(3);
    }

    @Override
    public int getAttackDamage() {
        int totalDamage = damage;
        for (BossPart part : subParts) {
            totalDamage += part.getAttackDamage();
        }
        return totalDamage;
    }

    public void grow(BedOfChaos boss) {
        Branch currentBranch = this;
        while (true) {
            if (random.nextBoolean()) {
                Branch newBranch = new Branch();
                currentBranch.subParts.add(newBranch);
                System.out.println("Branch grows a new Branch!");
                currentBranch = newBranch;
            } else {
                currentBranch.subParts.add(new Leaf());
                boss.heal(5);
                System.out.println("Branch grows a new Leaf!");

                break;
            }
        }
    }
}
