import helpers.CoinHelper;
import helpers.VendingMachineHelper;
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

    @Test
    public void given_coinsInMachine_when_returnCoinsButtonPressed_then_returnAllCoins() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();
        Coin nickelCoin1 = CoinHelper.getNickel();
        Coin nickelCoin2 = CoinHelper.getNickel();
        Coin dimeCoin = CoinHelper.getDime();
        Coin quarterCoin = CoinHelper.getQuarter();

        vendingMachine.insert(nickelCoin1);
        vendingMachine.insert(nickelCoin2);
        vendingMachine.insert(dimeCoin);
        vendingMachine.insert(quarterCoin);

        List<Coin> expectedReturnedCoins = List.of(nickelCoin1, nickelCoin2, dimeCoin, quarterCoin);
        assertEquals(expectedReturnedCoins, vendingMachine.returnCoins());
    }

    @Test
    public void given_coinsInMachine_when_returnCoinsButtonPressed_then_displayInsertCoin() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();
        Coin nickelCoin1 = CoinHelper.getNickel();
        Coin nickelCoin2 = CoinHelper.getNickel();
        Coin dimeCoin = CoinHelper.getDime();
        Coin quarterCoin = CoinHelper.getQuarter();

        vendingMachine.insert(nickelCoin1);
        vendingMachine.insert(nickelCoin2);
        vendingMachine.insert(dimeCoin);
        vendingMachine.insert(quarterCoin);
        vendingMachine.returnCoins();

        assertEquals("INSERT COIN", vendingMachine.seeDisplay());
    }
}
