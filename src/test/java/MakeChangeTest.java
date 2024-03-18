import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakeChangeTest {

    CoinHelper coinHelper = new CoinHelper();

    @Test
    public void given_moreCoinThanNeededInMachine_when_selectProduct_then_RemainingCoinInCoinReturn() {
        CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);
        Map<Products, Integer> availableProducts = new HashMap<>(Map.ofEntries(
                Map.entry(Products.COLA, 10),
                Map.entry(Products.CANDY, 5),
                Map.entry(Products.CHIPS, 1)
        ));

        VendingMachine vendingMachine = new VendingMachine(coinValidator, availableProducts);
        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);

        List<Coin> returnedCoins = vendingMachine.checkCoinReturn();
        assertEquals(0.25, returnedCoins.stream()
                                    .map(coinValidator::validateCoin)
                                    .mapToDouble(validatedCoin -> validatedCoin != null ? validatedCoin.getValue() : 0)
                                    .sum());
    }
}
