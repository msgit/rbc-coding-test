package com.msadal;

import com.msadal.domain.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

public class BasketVisitorTestCase {

    private static Collection<Fruit> basket;

    @BeforeClass
    public static void initTest() {
        basket = Arrays.asList(new Fruit[] {new Banana(0.3d), new Banana(0.2d), new Apple(0.1d), new Peach(0.1d), new Apple(0.17d), new Orange(0.2d), new Banana(0.14d), new Orange(0.18d)});
    }

    @Test
    public void testSimpleBasketVisitor() throws Exception {
        SimpleBasketPriceVisitor basketPriceVisitor = new SimpleBasketPriceVisitor();
        basketPriceVisitor.process(basket);
        assertEquals(4.44d, basketPriceVisitor.getTotalPrice());
    }

    @Test
    public void testConcurrentBasketVisitor() throws Exception {
        ConcurrentBasketPriceVisitor basketPriceVisitor = new ConcurrentBasketPriceVisitor();
        basketPriceVisitor.process(basket);
        assertEquals(4.44d, basketPriceVisitor.getTotalPrice());
    }
}
