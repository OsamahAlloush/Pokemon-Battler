package org.example;

import java.util.Random;
import java.util.Scanner;

public class BattleEngine {

    public void startBattle(Fighter player, Fighter cpu, Scanner scanner) {
        Pokemon p1 = player.getCurrentPokemon();
        Pokemon p2 = cpu.getCurrentPokemon();
        Random random = new Random();

        System.out.println("\n=== BATTLE START ===");
        System.out.println("A wild " + p2.getName() + " appeared!");
        System.out.println("Go! " + p1.getName() + "!");

        boolean isPlayerTurn = random.nextBoolean();
        if (isPlayerTurn) {
            System.out.println(p1.getName() + " won the coin flip and goes first!");
        } else {
            System.out.println(p2.getName() + " won the coin flip and goes first!");
        }

        while (p1.getCurrentHp() > 0 && p2.getCurrentHp() > 0) {
            System.out.println("\n--- New Turn ---");
            System.out.println(p1.getName() + " HP: " + p1.getCurrentHp() + "/" + p1.getMaxHp());
            System.out.println(p2.getName() + " HP: " + p2.getCurrentHp() + "/" + p2.getMaxHp());

            if (isPlayerTurn) {
                Attack p1Attack = player.chooseAttack(scanner);
                p1Attack.execute(p1, p2);

                if (p2.getCurrentHp() == 0) {
                    System.out.println("\n" + p2.getName() + " fainted! You won! 🎉");
                    break;
                }
            } else {
                Attack cpuAttack = cpu.chooseAttack(scanner);
                cpuAttack.execute(p2, p1);

                if (p1.getCurrentHp() == 0) {
                    System.out.println("\n" + p1.getName() + " fainted! You lost... 😢");
                    break;
                }
            }

            isPlayerTurn = !isPlayerTurn;
        }

        if (p1.getCurrentHp() <= 0) {
            p1.setLosses(p1.getLosses() + 1);
            p2.setWins(p2.getWins() + 1);
            System.out.println(p2.getName() + " wins! Stats Updated.");

        } else if (p2.getCurrentHp() <= 0) {
            p2.setLosses(p2.getLosses() + 1);
            p1.setWins(p1.getWins() + 1);
            System.out.println(p1.getName() + " wins! Stats Updated.");
        }

        System.out.println("=== BATTLE END ===");
    }
}