package org.example;

public class CoinValidator {

    private final float weightMargin;
    private final float sizeMargin;

    public CoinValidator(float weightMargin, float sizeMargin) {
        this.weightMargin = weightMargin;
        this.sizeMargin = sizeMargin;
    }

    public AcceptedCoinTypes validateCoin(Coin coin) {
        for(var coinType : AcceptedCoinTypes.values()) {
            if (isWithinWeightMargin(coin.weightInGrams(), coinType.getWeight())
                    && isWithinSizeMargin(coin.sizeInInch(), coinType.getSize())) {
                return coinType;
            }
        }

        return null;
    }

    public boolean isWithinWeightMargin(float coinWeight, float typeWeight) {
        return coinWeight > typeWeight - weightMargin && coinWeight < typeWeight + weightMargin;
    }

    public boolean isWithinSizeMargin(float coinSize, float typeSize) {
        return coinSize > typeSize - sizeMargin && coinSize < typeSize + sizeMargin;
    }
}
