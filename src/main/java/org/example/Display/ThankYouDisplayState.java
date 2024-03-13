package org.example.Display;

import org.example.AcceptedCoinTypes;
import org.example.Display.InsertCoinDisplayState;
import org.example.VendingMachine;

public class ThankYouDisplayState extends AcceptedCoinTypes.DisplayState {

    public ThankYouDisplayState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    String getDisplayValue() {
        vendingMachine.changeDisplayState(new InsertCoinDisplayState(vendingMachine));
        return "THANK YOU";
    }
}
