package org.example;

import java.util.Scanner;

public class HumanPlayer extends Fighter {

    public HumanPlayer(Pokemon currentPokemon) {
        super(currentPokemon);
    }

    @Override
    public Attack chooseAttack(Scanner scanner) {
        while (true) {
            System.out.println("\nYour Turn! Choose an attack for " + currentPokemon.getName() + " (or 0 to flee/cancel):");
            for (int i = 0; i < currentPokemon.getAttacks().size(); i++) {
                System.out.println((i + 1) + ". " + currentPokemon.getAttacks().get(i).getName());
            }

            System.out.print("Enter attack number: ");
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return null;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice > 0 && choice <= currentPokemon.getAttacks().size()) {
                    return currentPokemon.getAttacks().get(choice - 1);
                } else {
                    System.out.println("Invalid choice. Please enter a valid number from the list.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Please enter numbers only, not letters.");
            }
        }
    }
}