import org.example.Coin;
import org.example.CoinValidator;
import org.example.Products;
import org.example.VendingMachine;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnCoinsTest {

    CoinHelper coinHelper = new CoinHelper();

    private VendingMachine getBasicVendingMachine() {
        CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);
        Map<Products, Integer> availableProducts = new HashMap<>(Map.ofEntries(
                Map.entry(Products.COLA, 10),
                Map.entry(Products.CANDY, 5),
                Map.entry(Products.CHIPS, 1)
        ));
        return new VendingMachine(coinValidator, availableProducts);
    }

    @Test
    public void given_coinsInMachine_when_returnCoinsButtonPressed_then_returnAllCoins() {
        VendingMachine vendingMachine = getBasicVendingMachine();
        Coin nickelCoin1 = coinHelper.getNickel();
        Coin nickelCoin2 = coinHelper.getNickel();
        Coin dimeCoin = coinHelper.getDime();
        Coin quarterCoin = coinHelper.getQuarter();

        vendingMachine.insert(nickelCoin1);
        vendingMachine.insert(nickelCoin2);
        vendingMachine.insert(dimeCoin);
        vendingMachine.insert(quarterCoin);

        List<Coin> expectedReturnedCoins = List.of(nickelCoin1, nickelCoin2, dimeCoin, quarterCoin);
        assertEquals(expectedReturnedCoins, vendingMachine.returnCoins());
    }

    @Test
    public void given_coinsInMachine_when_returnCoinsButtonPressed_then_displayInsertCoin() {
        VendingMachine vendingMachine = getBasicVendingMachine();
        Coin nickelCoin1 = coinHelper.getNickel();
        Coin nickelCoin2 = coinHelper.getNickel();
        Coin dimeCoin = coinHelper.getDime();
        Coin quarterCoin = coinHelper.getQuarter();

        vendingMachine.insert(nickelCoin1);
        vendingMachine.insert(nickelCoin2);
        vendingMachine.insert(dimeCoin);
        vendingMachine.insert(quarterCoin);
        vendingMachine.returnCoins();

        assertEquals("INSERT COIN", vendingMachine.seeDisplay());
    }
}
