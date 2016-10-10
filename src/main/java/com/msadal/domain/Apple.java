package com.msadal.domain;

import com.msadal.Visitor;

public class Apple extends Fruit {
    public Apple(double weight) {
        super(FruitType.Apple, weight);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
