package com.msadal.domain;

import com.msadal.Visitable;

public abstract class Fruit implements Visitable {
    private FruitType type;
    private double weight;

    public Fruit(FruitType type, double weight) {
        this.type = type;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
