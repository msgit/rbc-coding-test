package com.msadal;

import com.msadal.domain.*;

import java.util.Collection;

public abstract class BasketPriceVisitor implements Visitor {

    private double BAG_PRICE = 0.5d;

    @Override
    public void visit(Banana element) {
        // charge fixed price for each banana
        add2Total(FruitType.Banana.getPricePerEach());
    }

    @Override
    public void visit(Orange element) {
        // charge fixed price for each orange plus bag price
        add2Total(FruitType.Orange.getPricePerEach() + BAG_PRICE);
    }

    @Override
    public void visit(Apple element) {
        // charge per kilo plus bag price
        add2Total(FruitType.Apple.getPricePerKilo() * element.getWeight() + BAG_PRICE);
    }

    @Override
    public void visit(Lemon element) {
        // charge per kilo (no bag required)
        add2Total(FruitType.Lemon.getPricePerKilo() * element.getWeight());
    }

    @Override
    public void visit(Peach element) {
        // charge per kilo plus bag price
        add2Total(FruitType.Peach.getPricePerKilo() * element.getWeight() + BAG_PRICE);
    }

    public abstract double getTotalPrice();

    public void process(Collection<Fruit> basket) {
        basket.stream().forEach(fruit -> fruit.accept(this));
    }

    protected abstract void add2Total(double delta);
}
