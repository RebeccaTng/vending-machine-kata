package org.example;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private float currentAmount;

    public void accept(Coin coin) {
        if(coin.validateCoin()) {
            currentAmount += coin.getValue();
        } else {
            reject(coin);
        }
    }

    public void reject(Coin coin) {

    }

    public String display() {
        if (currentAmount == 0.0f) return "INSERT COIN";
        return "$" + currentAmount;
    }

    public List<Coin> coinReturn() {
        return new ArrayList<>();
    }
}
