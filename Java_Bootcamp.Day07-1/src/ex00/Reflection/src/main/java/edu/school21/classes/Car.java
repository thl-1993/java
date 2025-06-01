package edu.school21.classes;

import java.util.StringJoiner;

public class Car {
    private String model;
    private int speed;
    private double price;

    public Car() {
        this.model = "Default model";
        this.speed = 0;
        this.price = 0.0;
    }

    public Car(String model, int speed, double price) {
        this.model = model;
        this.speed = speed;
        this.price = price;
    }

    public int increaseSpeed(int increment) {
        this.speed += increment;
        return speed;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("model='" + model + "'")
                .add("speed=" + speed)
                .add("price=" + price)
                .toString();
    }
}