package com.msadal.domain;

import com.msadal.Visitor;

public class Orange extends Fruit {
    public Orange(double weight) {
        super(FruitType.Orange, weight);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
