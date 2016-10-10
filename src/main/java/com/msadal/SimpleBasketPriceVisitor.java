package com.msadal;

import com.msadal.domain.Fruit;
import org.apache.log4j.Logger;

import java.util.Collection;

public class SimpleBasketPriceVisitor extends BasketPriceVisitor {

    private final static Logger log = Logger.getLogger(SimpleBasketPriceVisitor.class);

    private double totalPrice;

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    protected void add2Total(double delta) {
        totalPrice += delta;
        log.info("Added " + delta + " to total price.");
    }
}
