package org.example.display;

import org.example.VendingMachine;

public class PriceDisplayState extends DisplayState {
    private float price;

    public PriceDisplayState(VendingMachine vendingMachine, float price) {
        super(vendingMachine);
        this.price = price;
    }

    @Override
    public String getDisplayValue() {
        if(vendingMachine.getCurrentAmount() == 0) {
            vendingMachine.changeDisplayState(new InsertCoinDisplayState(vendingMachine));
        } else {
            vendingMachine.changeDisplayState(new CurrentAmountDisplayState(vendingMachine));
        }
        return "$" + price;
    }
}
