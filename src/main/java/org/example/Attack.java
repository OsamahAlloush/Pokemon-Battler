package org.example;

public class Attack {
    private String name;
    private int baseDamage;
    private int accuracy;
    private Type type;
    public Attack(String name, int baseDamage, int accuracy, Type type) {
        setName(name);
        setBaseDamage(baseDamage);
        setAccuracy(accuracy);
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        if (name== null || name.trim().isEmpty()) {
            this.name = "Unknown Attack";
        }
        else {
            this.name = name;
        }
    }
    public int getBaseDamage(){
        return baseDamage;
    }
    public void setBaseDamage(int baseDamage){
        if (baseDamage > 0) {
            this.baseDamage = baseDamage;
        } else {
            this.baseDamage = 1;
        }
    }
    public int getAccuracy(){
        return accuracy;
    }
    public void setAccuracy(int accuracy){
        if (accuracy >= 0 && accuracy <= 100) {
            this.accuracy = accuracy;
        } else {
            this.accuracy = 100;
        }
    }
    public Type getType(){
        return type;
    }
    public void setType(Type type){
        this.type = type;
    }@Override
    public String toString() {
        return "Attack [name=" + name + ", baseDamage=" + baseDamage + ", accuracy=" + accuracy + ", type=" + type + "]";
    }
    public Attack(){

    }
}
