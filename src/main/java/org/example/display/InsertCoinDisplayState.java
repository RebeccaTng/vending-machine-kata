package org.example.display;

import org.example.VendingMachine;

public class InsertCoinDisplayState extends DisplayState {

    public InsertCoinDisplayState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public String getDisplayValue() {
        return "INSERT COIN";
    }
}
