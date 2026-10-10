
package org.example;

import java.util.Random;
import java.util.random.RandomGenerator;

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
        Random random = new Random();
        int hitChance = random.nextInt(100) + 1;
                if (hitChance > this.getAccuracy())
        {
            System.out.println(attacker.getName() + " missed!");
            return;

        }
        int currentHealth = attacker.getCurrentHp();
        int newHp = Math.min(attacker.getMaxHp(), currentHealth + healAmount);
        attacker.setCurrentHp(newHp);

        System.out.println(attacker.getName() + " used " + name + " and healed to " + newHp + " HP!");
    }
}