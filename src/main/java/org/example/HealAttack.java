
package org.example;

public class HealAttack extends Attack {
    private int healAmount;

    public HealAttack() {
    }

    public HealAttack(String name, int healAmount, int accuracy, Type type) {
        super(name, accuracy, type);
        setHealAmount(healAmount);
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        if (healAmount > 0) {
            this.healAmount = healAmount;
        } else {
            this.healAmount = 20;
        }
    }

    @Override
    public void execute(Pokemon attacker, Pokemon defender) {
        int currentHealth = attacker.getCurrentHp();
        int newHp = Math.min(attacker.getMaxHp(), currentHealth + healAmount);
        attacker.setCurrentHp(newHp);

        System.out.println(attacker.getName() + " used " + name + " and healed to " + newHp + " HP!");
    }
}