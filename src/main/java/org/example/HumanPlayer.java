package org.example;

import java.util.Scanner;

public class HumanPlayer extends Fighter {
    public HumanPlayer(Pokemon currentPokemon) {
        super(currentPokemon);
    }
    @Override
    public Attack chooseAttack(Scanner scanner){
        System.out.println("\nYour Turn! Choose an attack for " + currentPokemon.getName() + ":");
        for (int i = 0; i < currentPokemon.getAttacks().size(); i++) {
            System.out.println((i + 1) + ". " + currentPokemon.getAttacks().get(i).getName());
        }
        System.out.print("Enter attack number: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());
        return currentPokemon.getAttacks().get(choice - 1);
    }
}
