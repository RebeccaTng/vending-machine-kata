package org.example.display;

import org.example.VendingMachine;

public abstract class DisplayState {

    protected VendingMachine vendingMachine;

    public DisplayState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public abstract String getDisplayValue();
}
