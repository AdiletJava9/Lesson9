package kg.geeks.game.general;

import kg.geeks.game.players.*;

import java.util.Random;

public class RPG_Game {
    public static Random random = new Random();
    private static int roundNumber;

    public static void startGame() {
        Boss boss = new Boss(1000, 50, "Saruman");
        Warrior warrior1 = new Warrior(290, 10, "Bob");
        Warrior warrior2 = new Warrior(280, 20, "James");
        Magic magic = new Magic(270, 20, "Anton");
        Berserk berserk = new Berserk(260, 15, "Ragnar");
        Medic doc = new Medic(250, "Alex", 15);
        Medic assistant = new Medic(320, "Sultan", 5);
        Hero[] heroes = {warrior1, doc, magic, warrior2, berserk, assistant};

        printStatistics(boss, heroes);
        while (!isGameOver(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    private static void playRound(Boss boss, Hero[] heroes) {
        roundNumber++;
        boss.chooseDefence();
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0 && boss.getHealth() > 0) {
                boss.attack(hero);
                if (boss.getDefence() != hero.getAbility()) {
                    if (hero instanceof Fighter) {
                        ((Fighter) hero).attack(boss);
                    }
                    hero.applySuperPower(boss, heroes);
                }
            }
        }
        printStatistics(boss, heroes);
    }

    private static boolean isGameOver(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    private static void printStatistics(Boss boss, Hero[] heroes) {
        System.out.println("ROUND  " + roundNumber + " --------------");
        System.out.println(boss);
        for (Hero hero : heroes) {
            System.out.println(hero);
        }
    }
}
