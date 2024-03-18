package org.example.display;

import org.example.VendingMachine;

public class CurrentAmountDisplayState extends DisplayState {

    public CurrentAmountDisplayState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public String getDisplayValue() {
        return vendingMachine.getCurrentAmount().asDollarString();
    }
}
