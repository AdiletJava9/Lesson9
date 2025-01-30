package kg.geeks.game.players;

import kg.geeks.game.general.RPG_Game;

public class Boss extends GameEntity implements Fighter {
    private SuperAbility defence;

    public Boss(int health, int damage, String name) {
        super(health, damage, name);
    }

    public void chooseDefence() {
        SuperAbility[] variants = SuperAbility.values();
        int randomIndex = RPG_Game.random.nextInt(variants.length); // 0,1,2,3
        this.defence = variants[randomIndex];
    }


    public SuperAbility getDefence() {
        return defence;
    }

    @Override
    public void attack(GameEntity entity) {
        if (entity instanceof Berserk &&
                this.defence != SuperAbility.BLOCK_AND_REVERT) {
            ((Berserk) entity).setBlockedDamage(
                    RPG_Game.random.nextInt(1, 3) * 5); // 5, 10
            entity.setHealth(entity.getHealth() - (this.getDamage() -
                    ((Berserk) entity).getBlockedDamage()));
        } else {
            entity.setHealth(entity.getHealth() - this.getDamage());
        }
    }

    @Override
    public String toString() {
        return super.toString() + " defence: " + defence;
    }
}
