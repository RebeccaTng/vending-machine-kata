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

public class AcceptCoinsTest {

    @Test
    public void given_validCoin_when_vendingMachineReceivesCoin_then_coinAcceptedAndDisplayUpdated() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();

        vendingMachine.insert(CoinHelper.getNickel());

        assertEquals("$0.05", vendingMachine.seeDisplay());
    }

    @Test
    public void given_invalidCoin_when_vendingMachineReceivesCoin_then_displayRemainUnchanged() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();
        vendingMachine.insert(CoinHelper.getPenny());
        assertEquals("INSERT COIN", vendingMachine.seeDisplay());

        vendingMachine.insert(CoinHelper.getNickel());
        vendingMachine.insert(CoinHelper.getPenny());
        assertEquals("$0.05", vendingMachine.seeDisplay());
    }

    @Test
    public void given_noCoins_when_vendingMachineUsed_then_showInsertCoinOnDisplay() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();

        assertEquals("INSERT COIN", vendingMachine.seeDisplay());
    }

    @Test
    public void given_invalidCoin_when_vendingMachineReceivesCoin_then_coinReturnedInCoinReturn() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();
        Coin pennyCoin1 = CoinHelper.getPenny();
        Coin pennyCoin2 = CoinHelper.getPenny();
        Coin nickelCoin = CoinHelper.getNickel();

        vendingMachine.insert(pennyCoin1);
        vendingMachine.insert(nickelCoin);
        vendingMachine.insert(pennyCoin2);

        assertEquals("$0.05", vendingMachine.seeDisplay());

        List<Coin> expectedReturnedCoins = List.of(pennyCoin1, pennyCoin2);
        assertEquals(expectedReturnedCoins, vendingMachine.checkCoinReturn());
    }
}
