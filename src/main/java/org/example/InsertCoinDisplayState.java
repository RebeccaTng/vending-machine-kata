package org.example;

public class InsertCoinDisplayState extends DisplayState{

    public InsertCoinDisplayState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    String getDisplayValue() {
        return "INSERT COIN";
    }
}
