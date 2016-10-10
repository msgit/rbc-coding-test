package com.msadal;

import com.msadal.BasketPriceVisitor;
import com.msadal.ConcurrentBasketPriceVisitor;
import com.msadal.SimpleBasketPriceVisitor;
import com.msadal.domain.Fruit;

import java.util.Collection;

public class BasketVisitorFactory {

    public static BasketPriceVisitor create(boolean parallel) {
        return parallel?
                new ConcurrentBasketPriceVisitor():
                new SimpleBasketPriceVisitor();
    }
}
