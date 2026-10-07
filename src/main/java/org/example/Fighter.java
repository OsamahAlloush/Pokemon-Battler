package org.example;

import java.util.Scanner;

public abstract class Fighter {
    protected Pokemon currentPokemon;

    public Fighter(Pokemon currentPokemon) {
        this.currentPokemon = currentPokemon;
    }
    public Pokemon getCurrentPokemon() {
        return currentPokemon;
    }
    public abstract Attack chooseAttack(Scanner scanner);
}
