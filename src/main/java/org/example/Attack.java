package org.example;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "category", defaultImpl = DamageAttack.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DamageAttack.class, name = "damage"),
        @JsonSubTypes.Type(value = HealAttack.class, name = "heal")
})
public abstract class Attack {
    protected String name;
    protected int accuracy;
    protected Type type;


    public Attack() {
    }

    public Attack(String name, int accuracy, Type type) {
        setName(name);
        setAccuracy(accuracy);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unknown Attack";
        } else {
            this.name = name;
        }
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        if (accuracy >= 0 && accuracy <= 100) {
            this.accuracy = accuracy;
        } else {
            this.accuracy = 100;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Attack [name=" + name + ", accuracy=" + accuracy + ", type=" + type + "]";
    }


    public abstract void execute(Pokemon attacker, Pokemon defender);
}