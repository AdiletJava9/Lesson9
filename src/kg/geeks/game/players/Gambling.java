package kg.geeks.game.players;

import java.util.Random;

public class Gambling extends Hero implements Fighter {
    private Random random = new Random();

    public Gambling(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.THROWS_DICE);
    }

    @Override
    public void attack(GameEntity entity) {
        entity.setHealth(entity.getHealth() - this.getDamage());
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        int dice = random.nextInt(6) + 1;  // Генерируем число от 1 до 6
        int dice1 = random.nextInt(6) + 1; // Генерируем второе число от 1 до 6

        if (dice == dice1) {
            int damage = dice * dice1;  // Урон = произведение чисел
            boss.setHealth(boss.getHealth() - damage);
            System.out.println(this.getName() + " выбросил " + dice + " и " + dice1 + "! Совпадение! Босс получает " + damage + " урона.");
        } else {
            int damage = dice + dice1;  // Урон = сумма чисел
            Hero randomHero = getRandomHero(heroes);
            if (randomHero != null) {
                randomHero.setHealth(randomHero.getHealth() - damage);
                System.out.println(this.getName() + " выбросил " + dice + " и " + dice1 + ". Несовпадение! " + randomHero.getName() + " получает " + damage + " урона.");
            }
        }
    }

    // Вынесенный метод поиска случайного живого союзника
    private Hero getRandomHero(Hero[] heroes) {
        Hero randomHero;
        do {
            randomHero = heroes[random.nextInt(heroes.length)];
        } while (randomHero == this || randomHero.getHealth() <= 0); // Исключаем себя и мертвых героев
        return randomHero;
    }
}

