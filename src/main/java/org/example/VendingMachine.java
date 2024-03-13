package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    private float currentAmount;
    private final CoinValidator coinValidator;
    private Map<Products, Integer> availableProducts;
    private List<Coin> returnedCoins = new ArrayList<>();
    private DisplayState displayState;

    public VendingMachine(CoinValidator coinValidator, Map<Products, Integer> availableProducts) {
        this.coinValidator = coinValidator;
        this.availableProducts = availableProducts;
        displayState = new InsertCoinDisplayState(this);
    }

    public void accept(Coin coin) {
        AcceptedCoinTypes validatedCoin = coinValidator.validateCoin(coin);
        if(validatedCoin == null ) {
            reject(coin);
        } else {
            currentAmount += validatedCoin.getValue();
            changeDisplayState(new CurrentAmountDisplayState(this));
        }
    }

    public void reject(Coin coin) {
        returnedCoins.add(coin);
    }

    public List<Coin> coinReturn() {
        return returnedCoins;
    }

    public void selectProduct(Products product) {
        float price = product.getPrice();
        if(currentAmount >= price) {
            availableProducts.put(product, availableProducts.get(product) - 1);
            currentAmount -= price;
            changeDisplayState(new ThankYouDisplayState(this));
        } else {
            changeDisplayState(new PriceDisplayState(this, price));
        }
    }

    public void changeDisplayState(DisplayState displayState) {
        this.displayState = displayState;
    }

    public String seeDisplay() {
        return displayState.getDisplayValue();
    }

    public float getCurrentAmount() {
        return currentAmount;
    }
}
