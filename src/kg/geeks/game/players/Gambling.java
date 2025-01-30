package kg.geeks.game.players;

import kg.geeks.game.general.RPG_Game;


public class Gambling extends Hero implements Fighter {
    public Gambling(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.THROWS_DICE);
    }

    @Override
    public void attack(GameEntity entity) {
        entity.setHealth(entity.getHealth() - this.getDamage());
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        int dice = RPG_Game.random.nextInt(6) + 1;
        int dice1 = RPG_Game.random.nextInt(6) + 1;

        if (dice == dice1) {
            int damage = dice * dice1;
            boss.setHealth(boss.getHealth() - damage);
            System.out.println(this.getName() + " выбросил " + dice + " и " + dice1 + "! Совпадение! Босс получает " + damage + " урона.");
        } else {
            int damage = dice + dice1;
            Hero randomHero = getRandomHero(heroes);
            if (randomHero != null) {
                randomHero.setHealth(randomHero.getHealth() - damage);
                System.out.println(this.getName() + " выбросил " + dice + " и " + dice1 + ". Несовпадение! " + randomHero.getName() + " получает " + damage + " урона.");
            }
        }
    }

    private Hero getRandomHero(Hero[] heroes) {
        Hero randomHero;
        do {
            randomHero = heroes[RPG_Game.random.nextInt(heroes.length)];
        } while (randomHero == this || randomHero.getHealth() <= 0);
        return randomHero;
    }
}

