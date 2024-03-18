import helpers.CoinHelper;
import helpers.VendingMachineHelper;
import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MakeChangeTest {

    CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);

    private double calculateSumOfReturn(List<Coin> returnedCoins) {
        return returnedCoins.stream()
                .map(coinValidator::validateCoin)
                .mapToDouble(validatedCoin -> validatedCoin != null ? validatedCoin.getValue().asDouble() : 0)
                .sum();
    }

    private VendingMachine getStockedVendingMachine() {
        return VendingMachineHelper.getStockedVendingMachine(coinValidator);
    }

    @Test
    public void given_oneQuarterMoreThanProductPrice_when_selectProduct_then_RemainingCoinReturned() {
        VendingMachine vendingMachine = getStockedVendingMachine();
        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals(0.25, calculateSumOfReturn(vendingMachine.checkCoinReturn()));
    }

    @Test
    public void given_multipleDifferentCoinsMoreThanProductPrice_when_selectProduct_then_RemainingCoinReturned() {
        VendingMachine vendingMachine = getStockedVendingMachine();
        vendingMachine.insert(CoinHelper.getQuarter());
        for (int i = 0; i < 10 ; i++) {
            vendingMachine.insert(CoinHelper.getDime());
        }
        for (int i = 0; i < 5 ; i++) {
            vendingMachine.insert(CoinHelper.getNickel());
        }
        vendingMachine.selectProduct(Products.COLA);

        assertEquals(0.5, calculateSumOfReturn(vendingMachine.checkCoinReturn()));
    }

    @Test
    public void given_invalidCoinsAndDifferentCoinsMoreThanProductPrice_when_selectProduct_then_AllCoinReturned() {
        VendingMachine vendingMachine = getStockedVendingMachine();
        Coin invalidCoin = CoinHelper.getPenny();

        vendingMachine.insert(invalidCoin);
        vendingMachine.insert(CoinHelper.getQuarter());
        for (int i = 0; i < 10 ; i++) {
            vendingMachine.insert(CoinHelper.getDime());
        }
        for (int i = 0; i < 5 ; i++) {
            vendingMachine.insert(CoinHelper.getNickel());
        }
        vendingMachine.selectProduct(Products.COLA);

        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertTrue(returnedCoins.contains(invalidCoin));
        assertEquals(0.5, calculateSumOfReturn(returnedCoins));
    }
}
