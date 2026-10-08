package org.example;

public class DamageAttack extends Attack {
    private int baseDamage;

    public DamageAttack() {

    }

    public DamageAttack(String name, int baseDamage, int accuracy, Type type) {
        super(name, accuracy, type);
        setBaseDamage(baseDamage);
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        if (baseDamage > 0) {
            this.baseDamage = baseDamage;
        } else {
            this.baseDamage = 1;
        }
    }

    @Override
    public void execute(Pokemon attacker, Pokemon defender) {
        int currentHealth = defender.getCurrentHp();
        int newHp = Math.max(0, currentHealth - baseDamage);
        defender.setCurrentHp(newHp);

        System.out.println(attacker.getName() + " used " + name + " and dealt " + baseDamage + " damage!");
    }
}