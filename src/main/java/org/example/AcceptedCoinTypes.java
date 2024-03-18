package org.example;

import org.example.money.DollarWrapper;

public enum AcceptedCoinTypes {
    // PENNY(2.5f, 0.75f, 0.01f),
    NICKEL(5.0f, 0.84f, DollarWrapper.valueOf("$0.05")),
    DIME(2.3f, 0.7f, DollarWrapper.valueOf("$0.1")),
    QUARTER(5.7f, 1f, DollarWrapper.valueOf("$0.25"));

    private final float weight;
    private final float size;
    private final DollarWrapper value;

    AcceptedCoinTypes(float weightInGram, float sizeInInch, DollarWrapper value) {
        this.weight = weightInGram;
        this.size = sizeInInch;
        this.value = value;
    }

    public float getSize() {
        return size;
    }

    public DollarWrapper getValue() {
        return value;
    }

    public float getWeight() {
        return weight;
    }
}
