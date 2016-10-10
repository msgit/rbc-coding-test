package com.msadal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msadal.domain.Fruit;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class AppRunner {

    private final static Logger log = Logger.getLogger(AppRunner.class);

    private static String basketJson = null;
    private static boolean parallel = true;

    private static void parseCommandLineParams(String[] args) {
        boolean error = false;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].startsWith("basketJson")) {
                basketJson = args[i].substring(11);
                if (!Files.exists(Paths.get(basketJson))) {
                    log.warn("Given basketJson path doesn't exist.");
                    basketJson = null;
                }
            }
            if (args[i].startsWith("parallel")) {
                parallel = Boolean.TRUE.toString().equals(args[i].substring(9));
            }
        }
        log.info("Basket json = " + basketJson + (basketJson == null? " (using random basket)": ""));
        log.info("Parallel = " + parallel);
    }

    public static void main( String[] args ) throws Exception {
        String usageMessage = "Usage\n" +
                "java AppRunner [basketJson=Path_json_file] [parallel=[true|false]]";
        System.out.println(usageMessage);

        parseCommandLineParams(args);

        Collection<Fruit> basket = BasketFactory.create(basketJson);
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        String json = gsonBuilder.create().toJson(basket);
        log.info("Given Basket: \n==========================================\n" + json);

        BasketPriceVisitor basketVisitor = BasketVisitorFactory.create(parallel);
        basketVisitor.process(basket);
        double total = basketVisitor.getTotalPrice();

        log.info("Total price for given basket is " + total);
    }
}
