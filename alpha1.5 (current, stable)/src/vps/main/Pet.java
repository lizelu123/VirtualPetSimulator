package vps.main;

import java.io.Serializable;

/**
 * Created by jsomani on 3/23/2016.
 */
public class Pet implements Serializable {
    String name;
    int age;
    double happiness;
    double hunger;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    double health;
    int food = 10;
    int medpacks = 10;
    boolean alive = true;
    double money = 2000;

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", happiness=" + happiness +
                ", hunger=" + hunger +
                ", health=" + health +
                ", food=" + food +
                ", medpacks=" + medpacks +
                ", money=" + money +
                ", alive=" + alive +
                '}';
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getMedpacks() {
        return medpacks;
    }

    public void setMedpacks(int medpacks) {
        this.medpacks = medpacks;
    }

    public Pet(String name, int age, double happiness, double hunger, double health) {
        this.name = name;
        this.age = age;
        this.happiness = happiness;
        this.hunger = hunger;
        this.health = health;

        if(getHealth() <= 0 ) {
            setAlive(false);
        }

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHappiness() {
        return happiness;
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

    public double getHunger() {
        return hunger;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}
