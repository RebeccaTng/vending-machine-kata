package org.example;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private float currentAmount;
    private final List<String> validCoinTypes = List.of("nickel", "dime", "quarter");

    public void accept(Coin coin) {
        if(validCoinTypes.contains(coin.type)) {
            currentAmount += coin.value;
        } else {
            reject(coin);
        }
    }

    public void reject(Coin coin) {

    }

    public String display() {
        return "$" + currentAmount;
    }

    public List<Coin> coinReturn() {
        return new ArrayList<>();
    }
}
