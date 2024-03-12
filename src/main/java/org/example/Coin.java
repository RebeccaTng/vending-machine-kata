package org.example;

public class Coin {
    float weightInGrams;
    float sizeInInch;
    String type;
    float value;

    public Coin(float weightInGram, float sizeInInch) {
        this.weightInGrams = weightInGram;
        this.sizeInInch = sizeInInch;
    }

    public boolean validateCoin() {
        for(var coinType : AcceptedCoinTypes.values()) {
            if (coinType.getWeight() == weightInGrams && coinType.getSize() == sizeInInch) {
                type = coinType.name().toLowerCase();
                value = coinType.getValue();
                return true;
            }
        }

        return false;
    }

    public float getValue() {
        return value;
    }
}
