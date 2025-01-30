package kg.geeks.game.players;

import kg.geeks.game.general.RPG_Game;

public class Warrior extends Hero implements Fighter {
    public Warrior(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.CRITICAL_DAMAGE);
    }

    @Override
    public void attack(GameEntity entity) {
        entity.setHealth(entity.getHealth() - this.getDamage());
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        int coeff = RPG_Game.random.nextInt(2, 5); // 2, 3, 4
        boss.setHealth(boss.getHealth() - coeff * this.getDamage());
        System.out.println("Warrior " + this.getName() +
                " attacked " + coeff * this.getDamage());
    }
}
