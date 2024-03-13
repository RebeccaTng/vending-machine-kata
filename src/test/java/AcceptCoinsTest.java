import org.example.Coin;
import org.example.CoinValidator;
import org.example.VendingMachine;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptCoinsTest {

    CoinHelper coinHelper = new CoinHelper();

    @Test
    public void given_validCoin_when_vendingMachineReceivesCoin_then_coinAcceptedAndDisplayUpdated() {
        VendingMachine vendingMachine = new VendingMachine(new CoinValidator(0.25f, 0.05f));

        vendingMachine.accept(coinHelper.getNickel());

        assertEquals("$0.05", vendingMachine.display());
    }

    @Test
    public void given_invalidCoin_when_vendingMachineReceivesCoin_then_displayRemainUnchanged() {
        VendingMachine vendingMachine = new VendingMachine(new CoinValidator(0.25f, 0.05f));
        vendingMachine.accept(coinHelper.getPenny());
        assertEquals("INSERT COIN", vendingMachine.display());

        vendingMachine.accept(coinHelper.getNickel());
        vendingMachine.accept(coinHelper.getPenny());
        assertEquals("$0.05", vendingMachine.display());
    }

    @Test
    public void given_noCoins_when_vendingMachineUsed_then_showInsertCoinOnDisplay() {
        VendingMachine vendingMachine = new VendingMachine(new CoinValidator(0.25f, 0.05f));

        assertEquals("INSERT COIN", vendingMachine.display());
    }

    @Test
    public void given_invalidCoin_when_vendingMachineReceivesCoin_then_coinReturnedInCoinReturn() {
        VendingMachine vendingMachine = new VendingMachine(new CoinValidator(0.25f, 0.05f));
        Coin pennyCoin1 = coinHelper.getPenny();
        Coin pennyCoin2 = coinHelper.getPenny();
        Coin nickelCoin = coinHelper.getNickel();

        vendingMachine.accept(pennyCoin1);
        vendingMachine.accept(nickelCoin);
        vendingMachine.accept(pennyCoin2);

        assertEquals("$0.05", vendingMachine.display());

        List<Coin> expectedReturnedCoins = List.of(pennyCoin1, pennyCoin2);
        assertEquals(expectedReturnedCoins, vendingMachine.coinReturn());
    }
}
