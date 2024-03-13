package org.example.Display;

import org.example.AcceptedCoinTypes;
import org.example.VendingMachine;

public class CurrentAmountDisplayState extends AcceptedCoinTypes.DisplayState {

    public CurrentAmountDisplayState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    String getDisplayValue() {
        return "$" + vendingMachine.getCurrentAmount();
    }
}
