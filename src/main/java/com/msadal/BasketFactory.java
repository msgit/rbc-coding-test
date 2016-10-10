package com.msadal;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.msadal.domain.Banana;
import com.msadal.domain.Fruit;
import com.msadal.domain.FruitType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BasketFactory {

    public static  Collection<Fruit> create(String basketJson) throws Exception {
        return basketJson == null?
            createRandom(): createFromJson(Paths.get(basketJson));
    }

    private static Collection<Fruit> createRandom() throws Exception {
        Random rand = new Random(System.currentTimeMillis());
        int size = rand.nextInt(50);
        List<Fruit> basket = new LinkedList<>();
        for (int i = 0; i < size; ++i) {
            FruitType type = FruitType.forOrdinal(rand.nextInt(5));
            Class fruitClazz =  Class.forName("com.msadal.domain." + type.name());
            Constructor<Fruit> constructor = fruitClazz.getConstructor(Double.TYPE);
            basket.add(constructor.newInstance(0.2d +  (rand.nextDouble() / 10d)));
        }
        return basket;
    }

    private static Collection<Fruit> createFromJson(Path basketJson) throws Exception {
        Gson gson = new Gson();
        String json = Files.toString(basketJson.toFile(), Charsets.UTF_8);

        Type listType = new TypeToken<List<HashMap<String, String>>>(){}.getType();
        List<HashMap<String, String>> basketOfMaps = gson.fromJson(json, listType);
        Collection<Fruit> basket = new LinkedList<>();
       for ( Map<String, String> map : basketOfMaps) {
            FruitType type = FruitType.valueOf(map.get("type"));
            Class fruitClazz =  Class.forName("com.msadal.domain." + type.name());
            Constructor<Fruit> constructor = fruitClazz.getConstructor(Double.TYPE);
            basket.add(constructor.newInstance(Double.parseDouble(map.get("weight"))));
        }
        return basket;
    }
}
