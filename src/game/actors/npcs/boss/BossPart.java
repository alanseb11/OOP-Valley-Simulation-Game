package game.actors.npcs.boss;

public abstract class BossPart {
    protected final int damage;

    public BossPart(int damage) {
        this.damage = damage;
    }

    public int getAttackDamage() {
        return damage;
    }
}
