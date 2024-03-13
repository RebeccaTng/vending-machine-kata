package org.example;

public class ThankYouDisplayState extends DisplayState {

    public ThankYouDisplayState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    String getDisplayValue() {
        vendingMachine.changeDisplayState(new InsertCoinDisplayState(vendingMachine));
        return "THANK YOU";
    }
}
