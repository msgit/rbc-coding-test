package com.msadal.domain;

import com.msadal.Visitor;

public class Banana extends Fruit {
    public Banana(double weight) {
        super(FruitType.Banana, weight);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
