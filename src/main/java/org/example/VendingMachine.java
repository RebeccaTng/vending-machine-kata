package org.example;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private float currentAmount;
    private CoinValidator coinValidator;
    private List<Coin> returnedCoins = new ArrayList<>();

    public VendingMachine(CoinValidator coinValidator) {
        this.coinValidator = coinValidator;
    }

    public void accept(Coin coin) {
        AcceptedCoinTypes validatedCoin = coinValidator.validateCoin(coin);
        if(validatedCoin == null ) {
            reject(coin);
        } else {
            currentAmount += validatedCoin.getValue();
        }
    }

    public void reject(Coin coin) {
        returnedCoins.add(coin);
    }

    public String display() {
        if (currentAmount == 0.0f) return "INSERT COIN";
        return "$" + currentAmount;
    }

    public List<Coin> coinReturn() {
        return returnedCoins;
    }
}
