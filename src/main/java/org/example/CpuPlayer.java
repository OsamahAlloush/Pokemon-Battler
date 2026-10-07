package org.example;

import java.util.Random;
import java.util.Scanner;

public class CpuPlayer extends Fighter {
    private Random random;
    public CpuPlayer(Pokemon currentPokemon){
        super(currentPokemon);
        this.random = new Random();
    }
    @Override
    public Attack chooseAttack(Scanner scanner){
        System.out.println("\nCPU is thinking...");
        int numberOfAttacks = currentPokemon.getAttacks().size();
        int randomIndex = random.nextInt(numberOfAttacks);
        Attack chosenAttack = currentPokemon.getAttacks().get(randomIndex);
        System.out.println("CPU chose "+ chosenAttack.getName());
        return chosenAttack;
    }
}
