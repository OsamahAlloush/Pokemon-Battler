package org.example;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static InputHelper helper = new InputHelper(scanner);

    public static void main(String[] args) {
        helper.autoLoad();
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            int choice = readIntRange(1, 9);
            switch (choice) {
                case 1:
                    helper.displayAllPokemon();
                    break;
                case 2:
                    helper.addNewPokemon();
                    break;
                case 3:
                    helper.editExistingPokemon();
                    break;
                case 4:
                    helper.deleteExistingPokemon();
                    break;
                case 5:
                    helper.saveToJson();
                    break;
                case 6:
                    helper.loadFromJson();
                    break;
                case 7:
                    helper.startWildBattle();
                    break;
                case 8:
                    helper.seedData();
                    break;
                case 9:
                    System.out.println("Exit :");
                    helper.saveToJson();
                    isRunning = false;
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.println("\n--- Hello and Welcome to the Pokemon Pokedex ---");
        System.out.println("1. Show all the Pokemons");
        System.out.println("2. Add new Pokemon");
        System.out.println("3. Edit Pokemon");
        System.out.println("4. Delete Pokemon");
        System.out.println("5. Save to File");
        System.out.println("6. Load from File");
        System.out.println("7. Battle with a Wild Pokemon");
        System.out.println("8. Restore to Seeded Data");
        System.out.println("9. Exit");
        System.out.print("Enter your choice (1-9):");
    }

    static int readIntRange(int min, int max) {
        while (true) {
            try {

                String input = scanner.nextLine();
                int number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

        }
    }

}