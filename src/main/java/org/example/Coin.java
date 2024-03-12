package org.example;

public class Coin {
    float weightInGrams;
    float size;
    float value;
    String type;

    public Coin(float weightInGrams, float sizeInInches) {
        this.weightInGrams = weightInGrams;
        this.size = sizeInInches;
        this.type = determineType();
        this.value = determineValue();
    }

    public float determineValue() {
        // some calculation
        return 0.05f;
    }

    public String determineType() {
        //
        return "nickel";
    }
}
