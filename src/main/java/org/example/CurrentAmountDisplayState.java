package org.example;

public class CurrentAmountDisplayState extends DisplayState {

    public CurrentAmountDisplayState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    String getDisplayValue() {
        return "$" + vendingMachine.getCurrentAmount();
    }
}
