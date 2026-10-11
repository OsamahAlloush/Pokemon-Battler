package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHelper {
    private ArrayList<Pokemon> pokedex;
    private Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.pokedex = new ArrayList<>();
        this.scanner = scanner;
    }

    public void displayAllPokemon() {
        System.out.println("\n ... All the Pokemons... :");
        if (pokedex.isEmpty()) {
            System.out.println("No Pokemons in the pokedex yet");
        } else {
            for (int i = 0; i < pokedex.size(); i++) {
                Pokemon p = pokedex.get(i);
                System.out.println((i + 1) + ". " + p.getName() + " (Type: " + p.getType() +
                        " | HP: " + p.getCurrentHp() + "/" + p.getMaxHp() + ")");
                System.out.println("Stats: [Wins: " + p.getWins() + " | Losses: " + p.getLosses() + "]");

                if (p.getAttacks() != null && !p.getAttacks().isEmpty()) {
                    System.out.print("   Attacks: ");
                    for (int j = 0; j < p.getAttacks().size(); j++) {
                        Attack atk = p.getAttacks().get(j);

                        if (atk instanceof DamageAttack) {
                            System.out.print(atk.getName() + " (" + ((DamageAttack) atk).getBaseDamage() + " dmg)");
                        } else if (atk instanceof HealAttack) {
                            System.out.print(atk.getName() + " (" + ((HealAttack) atk).getHealAmount() + " heal)");
                        }

                        if (j < p.getAttacks().size() - 1) {
                            System.out.print(" | ");
                        }
                    }
                    System.out.println();
                } else {
                    System.out.println("   Attacks: No attacks yet!");
                }
            }
        }
    }

    // 2.Add new Pokemon
    public void addNewPokemon() {
        System.out.println("\n--- Add a New Pokemon ---");

        System.out.print("Enter Pokemon name: ");
        String newName;
        while (true) {
            newName = scanner.nextLine().trim();
            if (!newName.matches("[a-zA-Z ]+")) {
                System.out.print("Error: Name must contain letters only! Enter name again: ");
                continue;
            }
            boolean isDuplicated = false;
            for (Pokemon pokemon : pokedex) {
                if (pokemon.getName().equalsIgnoreCase(newName)) {
                    isDuplicated = true;
                    break;
                }
            }
            if (isDuplicated) {
                System.out.print("Error: Name already exists! Please try another name: ");
            } else {
                break;
            }
        }

        Type newType = null;
        while (true) {
            System.out.println("Choose Type: 1.NORMAL, 2.FIRE, 3.WATER, 4.GRASS, 5.ELECTRIC, 6.AIR, 7.FIGHTING, 8.DARK");
            System.out.print("Enter type number: ");
            String typeInput = scanner.nextLine().trim();
            switch (typeInput) {
                case "1": newType = Type.NORMAL; break;
                case "2": newType = Type.FIRE; break;
                case "3": newType = Type.WATER; break;
                case "4": newType = Type.GRASS; break;
                case "5": newType = Type.ELECTRIC; break;
                case "6": newType = Type.AIR; break;
                case "7": newType = Type.FIGHTING; break;
                case "8": newType = Type.DARK; break;
                default:
                    System.out.println("Invalid choice, please choose another number.");
                    continue;
            }
            break;
        }

        int newHp = 0;
        while (true) {
            System.out.print("Enter Max HP (must be greater than 0): ");
            try {
                newHp = Integer.parseInt(scanner.nextLine().trim());
                if (newHp > 0) {
                    break;
                } else {
                    System.out.println("HP cannot be zero or negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numbers only.");
            }
        }


        Pokemon newPokemon = new Pokemon(newName, newType, newHp, newHp);

        // --- Add attacks ---
        while (true) {
            if (newPokemon.getAttacks() != null && newPokemon.getAttacks().size() >= 4) {
                System.out.println("Maximum of 4 attacks reached for this Pokemon.");
                break;
            }

            System.out.print("Do you want to add an attack for " + newName + "? (y/n): ");
            String addAttackChoice = scanner.nextLine().trim().toLowerCase();

            if (addAttackChoice.equals("n") || addAttackChoice.equals("no")) {
                break;
            } else if (addAttackChoice.equals("y") || addAttackChoice.equals("yes")) {

                System.out.println("What kind of attack? 1. Damage  2. Healing");
                String attackStyle = scanner.nextLine().trim();

                if (!attackStyle.equals("1") && !attackStyle.equals("2")) {
                    System.out.println("Invalid choice! Attack creation canceled.");
                    continue;
                }

                System.out.print("Enter attack name: ");
                String atkName;
                while (true) {
                    atkName = scanner.nextLine().trim();
                    if (!atkName.matches("[a-zA-Z ]+")) {
                        System.out.print("Error: Name must contain letters only! Enter name again: ");
                        continue;
                    }
                    boolean isAttackDuplicated = false;
                    if (newPokemon.getAttacks() != null) {
                        for (Attack existingAttack : newPokemon.getAttacks()){
                            if (existingAttack.getName().equalsIgnoreCase(atkName)) {
                                isAttackDuplicated = true;
                                break;
                            }
                        }
                    }
                    if (isAttackDuplicated) {
                        System.out.print("Error: Name already exists! Please try another name: ");
                    } else {
                        break;
                    }
                }

                int effectValue = 0;
                while (true) {
                    if (attackStyle.equals("1")) {
                        System.out.print("Enter base damage: ");
                    } else {
                        System.out.print("Enter heal amount: ");
                    }

                    try {
                        effectValue = Integer.parseInt(scanner.nextLine().trim());
                        if (effectValue >= 0) break;
                        System.out.println("Value cannot be negative.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter numbers only.");
                    }
                }

                int acc = 0;
                while (true) {
                    System.out.print("Enter accuracy (1-100): ");
                    try {
                        acc = Integer.parseInt(scanner.nextLine().trim());
                        if (acc > 0 && acc <= 100) break;
                        System.out.println("Accuracy must be between 1 and 100.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter numbers only.");
                    }
                }


                if (attackStyle.equals("1")) {
                    newPokemon.addAttack(new DamageAttack(atkName, effectValue, acc, newPokemon.getType()));
                } else {
                    newPokemon.addAttack(new HealAttack(atkName, effectValue, acc, newPokemon.getType()));
                }

            }
        }

        pokedex.add(newPokemon);
        System.out.println("\nSuccess! " + newName + " has been completely added to the Pokedex.\n");
    }

    // 3. edit pokemon
    public void editExistingPokemon() {
        System.out.println("\n--- Edit Pokemon ---");
        if (pokedex.isEmpty()) {
            System.out.println("The Pokedex is empty! Nothing to edit.\n");
            return;
        }

        while (true) {
            for (int i = 0; i < pokedex.size(); i++) {
                System.out.println((i + 1) + ". " + pokedex.get(i).getName());
            }
            System.out.println("0. Return to main menu");
            System.out.print("Enter the number of the Pokemon you want to edit: ");
            try {
                int userInput = Integer.parseInt(scanner.nextLine().trim());
                if (userInput == 0) {
                    System.out.println("Returning to main menu.");
                    return;
                }
                int index = userInput - 1;
                if (index >= 0 && index < pokedex.size()) {

                    Pokemon p = pokedex.get(index);
                    boolean keepEditing = true;

                    while (keepEditing) {
                        System.out.println("\nEditing: " + p.getName());
                        System.out.println("1. Change Name");
                        System.out.println("2. Change Type");
                        System.out.println("3. Change HP");
                        System.out.println("4. Add Attack");
                        System.out.println("5. View and delete attacks");
                        System.out.println("0. Back to Pokemon list");
                        System.out.print("Choose an option: ");

                        String editChoice = scanner.nextLine().trim();
                        switch (editChoice) {
                            case "0":
                                System.out.println("Returning to Pokemon list...");
                                keepEditing = false;
                                break;
                            case "1":
                                System.out.print("Enter new name: ");
                                String updatedName;
                                while (true) {
                                    updatedName = scanner.nextLine().trim();
                                    if (!updatedName.matches("[a-zA-Z ]+")) {
                                        System.out.print("Error: Name must contain letters only! Enter name again: ");
                                        continue;
                                    }
                                    boolean isDuplicated = false;
                                    for (int i = 0; i < pokedex.size(); i++) {
                                        if (pokedex.get(i).getName().equalsIgnoreCase(updatedName)) {
                                            isDuplicated = true;
                                            break;
                                        }
                                    }
                                    if (isDuplicated) {
                                        System.out.print("Error: name already exists! Please try another name: ");
                                    } else {
                                        p.setName(updatedName);
                                        System.out.println("Name updated successfully!");
                                        break;
                                    }
                                }
                                break;
                            case "2":
                                while (true) {
                                    System.out.println("Choose Type: 1.NORMAL, 2.FIRE, 3.WATER, 4.GRASS, 5.ELECTRIC, 6.AIR, 7.FIGHTING, 8.DARK");
                                    System.out.print("Enter type number: ");
                                    String typeInput = scanner.nextLine().trim();
                                    switch (typeInput) {
                                        case "1": p.setType(Type.NORMAL); break;
                                        case "2": p.setType(Type.FIRE); break;
                                        case "3": p.setType(Type.WATER); break;
                                        case "4": p.setType(Type.GRASS); break;
                                        case "5": p.setType(Type.ELECTRIC); break;
                                        case "6": p.setType(Type.AIR); break;
                                        case "7": p.setType(Type.FIGHTING); break;
                                        case "8": p.setType(Type.DARK); break;
                                        default:
                                            System.out.println("Error: Invalid choice, please choose another number.");
                                            continue;
                                    }
                                    System.out.println("Type updated successfully!");
                                    break;
                                }
                                break;
                            case "3":
                                while (true) {
                                    System.out.print("Enter new HP: ");
                                    try {
                                        int newHp = Integer.parseInt(scanner.nextLine().trim());
                                        if (newHp > 0) {
                                            p.setCurrentHp(newHp);
                                            System.out.println("HP updated successfully!");
                                            break;
                                        } else {
                                            System.out.println("HP cannot be zero or negative.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input! Please enter numbers only.");
                                    }
                                }
                                break;
                            case "4":
                                if (p.getAttacks() != null && p.getAttacks().size() >= 4) {
                                    System.out.println("Error: This Pokemon already has 4 attacks. Delete one first.");
                                    break;
                                }

                                System.out.println("What kind of attack? 1. Damage  2. Healing");
                                String attackStyle = scanner.nextLine().trim();

                                if (!attackStyle.equals("1") && !attackStyle.equals("2")) {
                                    System.out.println("Invalid choice! Returning to menu...");
                                    break;
                                }

                                String atkName;
                                while (true) {
                                    System.out.print("Enter attack name: ");
                                    atkName = scanner.nextLine().trim();
                                    if (!atkName.matches("[a-zA-Z ]+")) {
                                        System.out.print("Error: Attack name must contain letters only! Enter name again: ");
                                        continue;
                                    }

                                    boolean isAttackDuplicated = false;
                                    if (p.getAttacks() != null) {
                                        for (Attack existingAttack : p.getAttacks()) {
                                            if (existingAttack.getName().equalsIgnoreCase(atkName)) {
                                                isAttackDuplicated = true;
                                                break;
                                            }
                                        }
                                    }

                                    if (isAttackDuplicated) {
                                        System.out.print("Error: This attack name already exists for this Pokemon! Enter another name: ");
                                    } else {
                                        break;
                                    }
                                }

                                int effectValue = 0;
                                while (true) {
                                    if (attackStyle.equals("1")) {
                                        System.out.print("Enter base damage: ");
                                    } else {
                                        System.out.print("Enter heal amount: ");
                                    }
                                    try {
                                        effectValue = Integer.parseInt(scanner.nextLine().trim());
                                        if (effectValue >= 0) break;
                                        System.out.println("Value cannot be negative.");
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input! Please enter numbers only.");
                                    }
                                }

                                int acc = 0;
                                while (true) {
                                    System.out.print("Enter accuracy (1-100): ");
                                    try {
                                        acc = Integer.parseInt(scanner.nextLine().trim());
                                        if (acc > 0 && acc <= 100) break;
                                        System.out.println("Accuracy must be between 1 and 100.");
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input! Please enter numbers only.");
                                    }
                                }

                                if (attackStyle.equals("1")) {
                                    p.addAttack(new DamageAttack(atkName, effectValue, acc, p.getType()));
                                } else {
                                    p.addAttack(new HealAttack(atkName, effectValue, acc, p.getType()));
                                }
                                System.out.println("Attack added successfully!");
                                break;
                            case "5":
                                System.out.println("\n--- View and Delete Attack ---");
                                if (p.getAttacks() == null || p.getAttacks().isEmpty()) {
                                    System.out.println("This Pokemon has no attacks to delete.");
                                    break;
                                }

                                System.out.println("Current Attacks for " + p.getName() + ":");
                                for (int j = 0; j < p.getAttacks().size(); j++) {
                                    Attack currentAtk = p.getAttacks().get(j);

                                    String effectStr = "";
                                    if (currentAtk instanceof DamageAttack) {
                                        effectStr = "Damage: " + ((DamageAttack) currentAtk).getBaseDamage();
                                    } else if (currentAtk instanceof HealAttack) {
                                        effectStr = "Heal: " + ((HealAttack) currentAtk).getHealAmount();
                                    }

                                    System.out.println((j + 1) + ". " + currentAtk.getName() +
                                            " (" + effectStr +
                                            " | Accuracy: " + currentAtk.getAccuracy() + "%)");
                                }

                                System.out.print("Enter the number of the attack to delete (0 to return): ");
                                try {
                                    int atkInput = Integer.parseInt(scanner.nextLine().trim());
                                    if (atkInput == 0) {
                                        System.out.println("Returning to edit menu.");
                                    } else {
                                        int atkIndex = atkInput - 1;
                                        if (atkIndex >= 0 && atkIndex < p.getAttacks().size()) {
                                            System.out.println("Success! Attack '" + p.getAttacks().get(atkIndex).getName() + "' deleted.");
                                            p.getAttacks().remove(atkIndex);
                                        } else {
                                            System.out.println("Error: Number out of range!");
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input! Please enter a number.");
                                }
                                break;
                            default:
                                System.out.println("Invalid option! Please enter a number between 0-5.");
                                break;
                        }
                    }
                } else {
                    System.out.println("Error: Number is out of range! Please enter a valid number.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numbers only.\n");
            }
        }
    }

    // delete existing pokemon----
    public void deleteExistingPokemon() {
        System.out.println("\n--- Delete a Pokemon ---");
        if (pokedex.isEmpty()) {
            System.out.println("The Pokedex is empty! Nothing to delete.\n");
            return;
        }
        while (true) {
            for (int i = 0; i < pokedex.size(); i++) {
                System.out.println((i + 1) + ". " + pokedex.get(i).getName());
            }
            System.out.println("0. Return to main menu");
            System.out.print("Enter the number of the Pokemon you want to delete: ");
            try {
                int userInput = Integer.parseInt(scanner.nextLine().trim());
                if (userInput == 0) {
                    System.out.println("Returning to main menu.");
                    break;
                }
                int index = userInput - 1;
                if (index < 0 || index >= pokedex.size()) {
                    System.out.println("Error: number is out of range! please enter valid number : \n ");
                } else {
                    System.out.println("Success!" + pokedex.get(index).getName() + " has been deleted from the Pokedex.\n");
                    pokedex.remove(index);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numbers only.\n");
            }
        }
    }

    public void saveToJson() {
        System.out.println("\n--- Save to JSON ---");
        if (pokedex.isEmpty()) {
            System.out.println("The Pokedex is empty! Nothing to save.");
            return;
        }
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new java.io.File("pokedex.json"), pokedex);
            System.out.println("Success! Data has been saved to pokedex.json");
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // ---Load from JSON ---
    public void loadFromJson() {
        System.out.println("\n--- Load from File ---");
        java.io.File file = new java.io.File("pokedex.json");

        if (!file.exists()) {
            System.out.println("File not found. Please save data first (option 5).\n");
            return;
        }

        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            pokedex = mapper.readValue(file, new com.fasterxml.jackson.core.type.TypeReference<ArrayList<Pokemon>>() {});
            System.out.println("Success! Data has been loaded from pokedex.json");
            System.out.println("Loaded " + pokedex.size() + " Pokemons.\n");

        } catch (com.fasterxml.jackson.core.JsonParseException | com.fasterxml.jackson.databind.JsonMappingException e) {
            System.out.println("Crash prevented! The save file (pokedex.json) is corrupt or invalid.");

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // --- Seed data ---
    public void seedData() {
        System.out.println("\n--- Restore to seeded data ---");
        pokedex.clear();

        Pokemon P1 = new Pokemon("Pikachu", Type.ELECTRIC, 100, 100);
        P1.addAttack(new DamageAttack("Thunderbolt", 75, 100, Type.ELECTRIC));
        P1.addAttack(new DamageAttack("Quick body attack", 50, 100, Type.ELECTRIC));

        Pokemon P2 = new Pokemon("Charmander" , Type.FIRE, 100, 100);
        P2.addAttack(new DamageAttack("Golden Fire", 55, 75, Type.FIRE));
        P2.addAttack(new DamageAttack("Tornado Fire", 65, 90, Type.FIRE));

        Pokemon P3 = new Pokemon("Squirtle", Type.WATER, 100, 100);
        P3.addAttack(new DamageAttack("Water Gun", 45, 100, Type.WATER));
        P3.addAttack(new DamageAttack("Water Flood", 70, 90, Type.WATER));

        Pokemon P4 = new Pokemon("Bulbasaur", Type.GRASS, 100, 100);
        P4.addAttack(new DamageAttack("Vine Whip", 50, 100, Type.GRASS));
        P4.addAttack(new HealAttack("Synthesis", 40, 100, Type.GRASS));

        Pokemon P5 = new Pokemon("Butterfly" , Type.AIR, 100 ,100);
        P5.addAttack(new DamageAttack("Sleep Powder", 30, 50, Type.AIR));
        P5.addAttack(new DamageAttack("Poison Powder",  25, 50, Type.AIR));

        Pokemon P6 = new Pokemon("Hitmonchamon", Type.FIGHTING, 100, 100);
        P6.addAttack(new DamageAttack("Fire Punch", 45, 85, Type.FIGHTING));
        P6.addAttack(new DamageAttack("Ice Punch", 50, 90, Type.FIGHTING));

        pokedex.add(P1);
        pokedex.add(P2);
        pokedex.add(P3);
        pokedex.add(P4);
        pokedex.add(P5);
        pokedex.add(P6);
        System.out.println("Success! Data has been restored to seeded data.\n");
    }

    // --- Auto load
    public void autoLoad() {
        java.io.File file = new java.io.File("pokedex.json");
        if (file.exists()) {
            loadFromJson();
        } else {
            seedData();
        }
    }

    public void startWildBattle() {
        System.out.println("\n--- Wild Pokemon Battle ---");

        if (pokedex.isEmpty()) {
            System.out.println("Error: Your Pokedex is empty! You need at least one Pokemon to battle.");
            return;
        }

        System.out.println("Choose your fighter:");
        for (int i = 0; i < pokedex.size(); i++) {
            System.out.println((i + 1) + ". " + pokedex.get(i).getName() + " (HP: " + pokedex.get(i).getCurrentHp() + "/" + pokedex.get(i).getMaxHp() + ")");
        }
        System.out.println("0. Cancel and go back");
        int choice = -1;
        while (true) {
            System.out.print("Enter Pokemon number: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number : ");
                continue;
            }

            if (choice == 0) {
                System.out.println("Battle canceled.");
                return;
            }

            if (choice < 1 || choice > pokedex.size()) {
                System.out.println("Error: Invalid selection! Please enter a number between 1 and " + pokedex.size() + " :");
                continue;
            }
            break;
        }
        Pokemon myPokemon = pokedex.get(choice - 1);

        if (myPokemon.getCurrentHp() <= 0) {
            System.out.println("Error: " + myPokemon.getName() + " has fainted (0 HP) and cannot fight! Edit it and heal first.");
            return;
        }

        if (myPokemon.getAttacks() == null || myPokemon.getAttacks().isEmpty()) {
            System.out.println("Error: " + myPokemon.getName() + " has no attacks! Edit your Pokemon to add attacks first.");
            return;
        }


        ArrayList<Pokemon> wildPool = new ArrayList<>();

        // --- Wild Pokemon ---
        Pokemon w1 = new Pokemon("Wild Pidgey", Type.AIR, 60, 60);
        w1.addAttack(new DamageAttack("Gust", 20, 100, Type.AIR));

        Pokemon w2 = new Pokemon("Wild Geodude", Type.FIGHTING, 90, 90);
        w2.addAttack(new DamageAttack("Rock Throw", 30, 90, Type.FIGHTING));

        Pokemon w3 = new Pokemon("Boss Mewtwo", Type.DARK, 150, 150);
        w3.addAttack(new DamageAttack("Shadow Ball", 50, 100, Type.DARK));
        w3.addAttack(new HealAttack("Recover", 40, 100, Type.DARK));

        Pokemon w4 = new Pokemon("Wild Charizard", Type.FIRE, 120, 120);
        w4.addAttack(new DamageAttack("Flamethrower", 40, 100, Type.FIRE));
        w4.addAttack(new DamageAttack("Scratch", 20, 100, Type.NORMAL));


        Pokemon w5 = new Pokemon("Wild Pikachu", Type.ELECTRIC, 80, 80);
        w5.addAttack(new DamageAttack("Thunder Shock", 35, 100, Type.ELECTRIC));
        w5.addAttack(new HealAttack("Rest", 30, 100, Type.NORMAL));

        Pokemon w6 = new Pokemon("Wild Snorlax", Type.NORMAL, 140, 140);
        w6.addAttack(new DamageAttack("Body Slam", 45, 90, Type.NORMAL));
        w6.addAttack(new HealAttack("Eat Berry", 50, 100, Type.NORMAL));

        Pokemon w7 = new Pokemon("Wild Gengar", Type.DARK, 100, 100);
        w7.addAttack(new DamageAttack("Shadow Punch", 40, 100, Type.DARK));
        w7.addAttack(new DamageAttack("Lick", 25, 100, Type.DARK));

        Pokemon w8 = new Pokemon("Wild Venusaur", Type.GRASS, 130, 130);
        w8.addAttack(new DamageAttack("Solar Beam", 60, 80, Type.GRASS));
        w8.addAttack(new HealAttack("Synthesis", 40, 100, Type.GRASS));

        Pokemon w9 = new Pokemon("Wild Blastoise", Type.WATER, 130, 130);
        w9.addAttack(new DamageAttack("Hydro Pump", 65, 80, Type.WATER));
        w9.addAttack(new DamageAttack("Bite", 35, 100, Type.NORMAL));

        wildPool.add(w1);
        wildPool.add(w2);
        wildPool.add(w3);
        wildPool.add(w4);
        wildPool.add(w5);
        wildPool.add(w6);
        wildPool.add(w7);
        wildPool.add(w8);
        wildPool.add(w9);

        java.util.Random rand = new java.util.Random();
        Pokemon wildPokemon = wildPool.get(rand.nextInt(wildPool.size()));

        System.out.println("\n!!! A wild " + wildPokemon.getName() + " appeared !!!");

        Fighter player = new HumanPlayer(myPokemon);
        Fighter cpu = new CpuPlayer(wildPokemon);

        BattleEngine engine = new BattleEngine();
        engine.startBattle(player, cpu, scanner);
    }
    // Statistics :
        public void showStatistics() {
            System.out.println("\n--- 📊 Game Statistics ---");
            if (pokedex.isEmpty()) {
                System.out.println("No Pokemons available.");
                return;
            }

            int totalWins = 0;
            int totalLosses = 0;
            Pokemon mostUsedPokemon = null;
            int maxBattles = -1;
            Attack mostUsedAttack = null;
            int maxAttackUsage = -1;

            for (Pokemon p : pokedex) {
                int pWins = p.getWins();
                int pLosses = p.getLosses();
                int pBattles = pWins + pLosses;

                totalWins += pWins;
                totalLosses += pLosses;


                if (pBattles > maxBattles) {
                    maxBattles = pBattles;
                    mostUsedPokemon = p;
                }

                double ratio = (pLosses == 0) ? pWins : (double) pWins / pLosses;
                System.out.printf("- %s -> Wins: %d | Losses: %d | W/L Ratio: %.2f\n", p.getName(), pWins, pLosses, ratio);

                if (p.getAttacks() != null) {
                    for (Attack a : p.getAttacks()) {
                        if (a.getUsageCount() > maxAttackUsage) {
                            maxAttackUsage = a.getUsageCount();
                            mostUsedAttack = a;
                        }
                    }
                }
            }

            System.out.println("\n--- Overall Totals ---");
            System.out.println("Total Wins (All Pokemons): " + totalWins);
            System.out.println("Total Losses (All Pokemons): " + totalLosses);

            if (mostUsedPokemon != null && maxBattles > 0) {
                System.out.println("Most Used Pokemon: " + mostUsedPokemon.getName() + " (" + maxBattles + " battles)");
            }

            if (mostUsedAttack != null && maxAttackUsage > 0) {
                System.out.println("Most Used Attack: " + mostUsedAttack.getName() + " (" + maxAttackUsage + " uses)");
            } else {
                System.out.println("Most Used Attack: None yet.");
            }
            System.out.println("--------------------------\n");
        }

    }
