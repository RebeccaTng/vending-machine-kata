package org.example.Display;

import org.example.AcceptedCoinTypes;
import org.example.VendingMachine;

public class InsertCoinDisplayState extends AcceptedCoinTypes.DisplayState {

    public InsertCoinDisplayState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    String getDisplayValue() {
        return "INSERT COIN";
    }
}
