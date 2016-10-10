package com.msadal.domain;

import com.msadal.Visitor;

public class Lemon extends Fruit {
    public Lemon(double weight) {
        super(FruitType.Lemon, weight);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
