package org.example;

import org.example.Display.CurrentAmountDisplayState;
import org.example.Display.InsertCoinDisplayState;
import org.example.Display.PriceDisplayState;
import org.example.Display.ThankYouDisplayState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    private float currentAmount;
    private final CoinValidator coinValidator;
    private Map<Products, Integer> availableProducts;
    private List<Coin> returnedCoins = new ArrayList<>();
    private AcceptedCoinTypes.DisplayState displayState;

    public VendingMachine(CoinValidator coinValidator, Map<Products, Integer> availableProducts) {
        this.coinValidator = coinValidator;
        this.availableProducts = availableProducts;
        displayState = new InsertCoinDisplayState(this);
    }

    public void insert(Coin coin) {
        AcceptedCoinTypes validatedCoin = coinValidator.validateCoin(coin);
        if(validatedCoin != null ) {
            accept(validatedCoin);
        } else {
            reject(coin);
        }
    }

    public List<Coin> checkCoinReturn() {
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

    public String seeDisplay() {
        return displayState.getDisplayValue();
    }

    public void changeDisplayState(AcceptedCoinTypes.DisplayState displayState) {
        this.displayState = displayState;
    }

    public float getCurrentAmount() {
        return currentAmount;
    }

    private void accept(AcceptedCoinTypes coin) {
        currentAmount += coin.getValue();
        changeDisplayState(new CurrentAmountDisplayState(this));
    }

    private void reject(Coin coin) {
        returnedCoins.add(coin);
    }
}
