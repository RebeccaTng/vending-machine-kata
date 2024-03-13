package org.example;

public abstract class DisplayState {

    protected VendingMachine vendingMachine;

    public DisplayState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    abstract String getDisplayValue();
}
