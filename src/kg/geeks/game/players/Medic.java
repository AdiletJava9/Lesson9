package kg.geeks.game.players;

public class Medic extends Hero {
    private final int healPoints;

    public Medic(int health, String name, int healPoints) {
        super(health, 0, name, SuperAbility.HEAL);
        this.healPoints = healPoints;
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0 && hero != this) {
                hero.setHealth(hero.getHealth() + healPoints);
            }
        }
    }
}
