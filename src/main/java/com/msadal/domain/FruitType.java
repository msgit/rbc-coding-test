package com.msadal.domain;

public enum FruitType {
    Banana(0.2d, null),
    Orange(0.3d, null),
    Apple(null, 2.0d),
    Lemon(null, 1.5d),
    Peach(null, 2.0d);

    private Double pricePerKilo;
    private Double pricePerEach;

    FruitType(Double pricePerEach, Double pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
        this.pricePerEach = pricePerEach;
    }

    public Double getPricePerKilo() {
        return pricePerKilo;
    }

    public Double getPricePerEach() {
        return pricePerEach;
    }

    public static FruitType forOrdinal(int ordinal) {
        for (FruitType type : values()) {
            if (type.ordinal() == ordinal) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown FruitType ordinal " + ordinal);
    }
}
