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
        double multiplier = 1.0;
        Type attackType = this.getType();
        Type defenderType = defender.getType();

        if (attackType != null && defenderType != null) {

            if ((attackType == Type.WATER && defenderType == Type.FIRE) ||
                    (attackType == Type.FIRE && defenderType == Type.GRASS) ||
                    (attackType == Type.GRASS && defenderType == Type.WATER) ||
                    (attackType == Type.ELECTRIC && defenderType == Type.WATER) ||
                    (attackType == Type.AIR && defenderType == Type.GRASS) ||
                    (attackType == Type.AIR && defenderType == Type.FIGHTING) ||
                    (attackType == Type.ELECTRIC && defenderType == Type.AIR)) {

                multiplier = 2.0;
                System.out.println(">>> It's super effective!");

            }

            else if ((attackType == Type.FIRE && defenderType == Type.WATER) ||
                    (attackType == Type.GRASS && defenderType == Type.FIRE) ||
                    (attackType == Type.WATER && defenderType == Type.GRASS) ||
                    (attackType == Type.ELECTRIC && defenderType == Type.GRASS) ||
                    (attackType == Type.FIGHTING && defenderType == Type.AIR) ||
                    (attackType == Type.GRASS && defenderType == Type.AIR)) {

                multiplier = 0.5;
                System.out.println(">>> It's not very effective...");
            }
        }


        int finalDamage = (int) (baseDamage * multiplier);
        int currentHealth = defender.getCurrentHp();
        int newHp = Math.max(0, currentHealth - finalDamage);
        defender.setCurrentHp(newHp);

        System.out.println(attacker.getName() + " used " + name + " and dealt " + finalDamage + " damage!");
    }
}