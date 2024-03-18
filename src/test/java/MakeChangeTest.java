import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakeChangeTest {

    CoinHelper coinHelper = new CoinHelper();
    CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);

    private VendingMachine getBasicVendingMachine() {
        Map<Products, Integer> availableProducts = new HashMap<>(Map.ofEntries(
                Map.entry(Products.COLA, 10),
                Map.entry(Products.CANDY, 5),
                Map.entry(Products.CHIPS, 1)
        ));

        return new VendingMachine(coinValidator, availableProducts);
    }

    private double calculateSumOfReturn(List<Coin> returnedCoins) {
        return returnedCoins.stream()
                .map(coinValidator::validateCoin)
                .mapToDouble(validatedCoin -> validatedCoin != null ? validatedCoin.getValue() : 0)
                .sum();
    }

    @Test
    public void given_oneQuarterMoreThanProductPrice_when_selectProduct_then_RemainingCoinReturned() {
        VendingMachine vendingMachine = getBasicVendingMachine();
        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals(0.25, calculateSumOfReturn(vendingMachine.checkCoinReturn()));
    }

    @Test
    public void given_multipleDifferentCoinsMoreThanProductPrice_when_selectProduct_then_RemainingCoinReturned() {
        VendingMachine vendingMachine = getBasicVendingMachine();
        vendingMachine.insert(coinHelper.getQuarter());
        for (int i = 0; i < 10 ; i++) {
            vendingMachine.insert(coinHelper.getDime());
        }
        vendingMachine.insert(coinHelper.getNickel());
        vendingMachine.insert(coinHelper.getNickel());
        vendingMachine.selectProduct(Products.COLA);

        assertEquals(0.35, calculateSumOfReturn(vendingMachine.checkCoinReturn()));
    }
}
