package com.msadal;

import com.msadal.domain.Fruit;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This class is threadsafe.
 */
public class ConcurrentBasketPriceVisitor extends BasketPriceVisitor {
    private final static Logger log = Logger.getLogger(ConcurrentBasketPriceVisitor.class);

    private AtomicReference<Double> totalPrice = new AtomicReference(0d);

    @Override
    public double getTotalPrice() {
        return totalPrice.get();
    }

    @Override
    protected void add2Total(double delta) {
        Double oldTotal = null;
        Double newTotal = null;
        do {
            oldTotal = totalPrice.get();
            newTotal = (delta + oldTotal.doubleValue());
        } while (!totalPrice.compareAndSet(oldTotal, newTotal));
        log.info("Added " + delta + " to total price.");
    }

    @Override
    public void process(Collection<Fruit> basket) {
        /*
         * It's rather bad idea to use this parallel stream on prod environment
         * because we don't have any control over it. It would be better
         * to use ExecutorService instead. I used it hear only because I wanted to
         * it looks cool and more elegant in coding tests.
         */
        log.info("Processing basket in parallel.");
        basket.parallelStream().forEach(fruit -> fruit.accept(this));
    }
}
