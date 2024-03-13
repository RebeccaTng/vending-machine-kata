import org.example.Coin;
import org.example.CoinValidator;
import org.example.Products;
import org.example.VendingMachine;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptCoinsTest {

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
    public void given_validCoin_when_vendingMachineReceivesCoin_then_coinAcceptedAndDisplayUpdated() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.accept(coinHelper.getNickel());

        assertEquals("$0.05", vendingMachine.seeDisplay());
    }

    @Test
    public void given_invalidCoin_when_vendingMachineReceivesCoin_then_displayRemainUnchanged() {
        VendingMachine vendingMachine = getBasicVendingMachine();
        vendingMachine.accept(coinHelper.getPenny());
        assertEquals("INSERT COIN", vendingMachine.seeDisplay());

        vendingMachine.accept(coinHelper.getNickel());
        vendingMachine.accept(coinHelper.getPenny());
        assertEquals("$0.05", vendingMachine.seeDisplay());
    }

    @Test
    public void given_noCoins_when_vendingMachineUsed_then_showInsertCoinOnDisplay() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        assertEquals("INSERT COIN", vendingMachine.seeDisplay());
    }

    @Test
    public void given_invalidCoin_when_vendingMachineReceivesCoin_then_coinReturnedInCoinReturn() {
        VendingMachine vendingMachine = getBasicVendingMachine();
        Coin pennyCoin1 = coinHelper.getPenny();
        Coin pennyCoin2 = coinHelper.getPenny();
        Coin nickelCoin = coinHelper.getNickel();

        vendingMachine.accept(pennyCoin1);
        vendingMachine.accept(nickelCoin);
        vendingMachine.accept(pennyCoin2);

        assertEquals("$0.05", vendingMachine.seeDisplay());

        List<Coin> expectedReturnedCoins = List.of(pennyCoin1, pennyCoin2);
        assertEquals(expectedReturnedCoins, vendingMachine.coinReturn());
    }
}
