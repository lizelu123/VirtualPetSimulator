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
    double health;

    public Pet(String name, int age, double happiness, double hunger, double health) {
        this.name = name;
        this.age = age;
        this.happiness = happiness;
        this.hunger = hunger;
        this.health = health;

    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", happiness=" + happiness +
                ", hunger=" + hunger +
                ", health=" + health +
                '}';
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
