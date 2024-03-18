package org.example;

import org.example.display.*;
import org.example.money.DollarWrapper;

import java.util.*;

public class VendingMachine {

    private final CoinValidator coinValidator;
    private Map<Products, Integer> availableProducts;

    private DisplayState displayState;
    private Map<AcceptedCoinTypes, List<Coin>> currentCoins = new HashMap<>();

    private DollarWrapper currentAmount = DollarWrapper.zero();
    private final List<Coin> returnedCoins = new ArrayList<>();

    public VendingMachine(CoinValidator coinValidator, Map<Products, Integer> availableProducts) {
        this.coinValidator = coinValidator;
        this.availableProducts = availableProducts;
        displayState = new InsertCoinDisplayState(this);
        for (AcceptedCoinTypes c : AcceptedCoinTypes.values()) {
            currentCoins.put(c, new ArrayList<>());
        }
    }

    /** AVAILABLE TO USER **/
    public void insert(Coin coin) {
        AcceptedCoinTypes validatedCoin = coinValidator.validateCoin(coin);
        if(validatedCoin != null ) {
            accept(validatedCoin, coin);
        } else {
            returnCoin(coin);
        }
    }

    public List<Coin> checkCoinReturn() {
        return returnedCoins;
    }

    public void selectProduct(Products product) {
        DollarWrapper price = product.getPrice();
        if(currentAmount.greaterThanOrEquals(price)) {
            int availableAmount = availableProducts.get(product);
            if(availableAmount > 0) {
                availableProducts.put(product, availableAmount - 1);
                handlePriceDeduction(price);
                changeDisplayState(new ThankYouDisplayState(this));
            }
        } else {
            changeDisplayState(new PriceDisplayState(this, price));
        }
    }

    public String seeDisplay() {
        return displayState.getDisplayValue();
    }

    /** FOR OTHER CLASSES **/
    public void changeDisplayState(DisplayState displayState) {
        this.displayState = displayState;
    }

    public DollarWrapper getCurrentAmount() {
        return currentAmount;
    }

    /** PRIVATE HELPER FUNCTIONS **/
    private void accept(AcceptedCoinTypes validatedCoin, Coin coin) {
        currentCoins.get(validatedCoin).add(coin);
        currentAmount = currentAmount.add(validatedCoin.getValue());
        changeDisplayState(new CurrentAmountDisplayState(this));
    }

    private void returnCoin(Coin coin) {
        returnedCoins.add(coin);
    }

    private void handlePriceDeduction(DollarWrapper price) {
        currentAmount = currentAmount.subtract(price);
        AcceptedCoinTypes[] sortedCoinTypes = AcceptedCoinTypes.values();
        Arrays.sort(sortedCoinTypes, Comparator.comparingDouble(type -> ((AcceptedCoinTypes) type).getValue().asDouble()).reversed());

        for(AcceptedCoinTypes coinType : sortedCoinTypes) {
            int neededCoins = (int) Math.floor(price.dividedBy(coinType.getValue()));
            List<Coin> availableCoins = currentCoins.get(coinType);

            for(int i = 0; !availableCoins.isEmpty() && i < neededCoins; i++) {
                availableCoins.remove(0);
                price = price.subtract(coinType.getValue());
            }
        }

        if(currentAmount.greaterThan(0)) {
            returnAllCoins();
        };
    }

    private void returnAllCoins() {
        for(List<Coin> coins : currentCoins.values()) {
            returnedCoins.addAll(coins);
            coins.clear();
        }
    }
}
