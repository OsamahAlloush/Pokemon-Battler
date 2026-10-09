package org.example;

import java.util.ArrayList;

public class Pokemon {
    private String name;
    private Type type;
    private int maxHp;
    private int currentHp;
    private int wins;
    private int losses;
    private ArrayList<Attack> attacks;


    public Pokemon() {
        this.attacks = new ArrayList<>();
    }

    public Pokemon(String name, Type type, int maxHp, int currentHp) {
        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.attacks = new ArrayList<>();
    }


    public String getName() {
        return name; }
    public void setName(String name) {
        this.name = name; }

    public Type getType() {
        return type; }
    public void setType(Type type) { this.type = type; }

    public int getMaxHp() {
        return maxHp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }
    public int getWins() {return wins;}

    public void setWins(int wins) {this.wins = wins;}
    public int getLosses() {return losses;}
    public void setLosses(int losses) {this.losses = losses;}

    public int getCurrentHp() {
        return currentHp; }
    public void setCurrentHp(int currentHp) { this.currentHp = currentHp; }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }
    public void setAttacks(ArrayList<Attack> attacks) {
        this.attacks = attacks;
    }
    public void addAttack(Attack attack) {

        if (this.attacks == null) {
            this.attacks = new ArrayList<>();
        }
        if (this.attacks.size() >= 4) {
            System.out.println("Error: This Pokemon already has 4 attacks! You cannot add more.");
        } else {
            this.attacks.add(attack);
           // System.out.println("Attack added successfully!");
        }
    }
}