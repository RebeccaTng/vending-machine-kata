package org.example.display;

import org.example.VendingMachine;

public class ThankYouDisplayState extends DisplayState {

    public ThankYouDisplayState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public String getDisplayValue() {
        vendingMachine.changeDisplayState(new InsertCoinDisplayState(vendingMachine));
        return "THANK YOU";
    }
}
