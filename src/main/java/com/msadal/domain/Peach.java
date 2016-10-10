package com.msadal.domain;

import com.msadal.Visitor;

public class Peach extends Fruit {
    public Peach(double weight) {
        super(FruitType.Peach, weight);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
