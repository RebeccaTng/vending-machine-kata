package org.example;

public enum AcceptedCoinTypes {
    // PENNY(2.5f, 0.75f, 0.01f),
    NICKEL(5f, 0.84f, 0.05f),
    DIME(2.3f, 0.7f, 0.1f),
    QUARTER(5.7f, 1f, 0.25f);

    private final float weight;
    private final float size;
    private final float value;

    AcceptedCoinTypes(float weightInGram, float sizeInInch, float value) {
        this.weight = weightInGram;
        this.size = sizeInInch;
        this.value = value;
    }

    public float getSize() {
        return size;
    }

    public float getValue() {
        return value;
    }

    public float getWeight() {
        return weight;
    }
}
