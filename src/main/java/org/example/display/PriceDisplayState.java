package org.example.display;

import org.example.VendingMachine;
import org.example.money.DollarWrapper;

public class PriceDisplayState extends DisplayState {
    private DollarWrapper price;

    public PriceDisplayState(VendingMachine vendingMachine, DollarWrapper price) {
        super(vendingMachine);
        this.price = price;
    }

    @Override
    public String getDisplayValue() {
        if(vendingMachine.getCurrentAmount().equalsTo(0)) {
            vendingMachine.changeDisplayState(new InsertCoinDisplayState(vendingMachine));
        } else {
            vendingMachine.changeDisplayState(new CurrentAmountDisplayState(vendingMachine));
        }
        return price.asDollarString();
    }
}
