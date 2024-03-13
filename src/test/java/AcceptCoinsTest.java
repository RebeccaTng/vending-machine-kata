import org.example.Coin;
import org.example.CoinValidator;
import org.example.VendingMachine;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptCoinsTest {

    public Coin getPenny() {
        return new Coin(2.5f, 0.75f);
    }

    public Coin getNickel() {
        return new Coin(5.0f, 0.84f);
    }

    @Test
    public void when_vendingMachineReceivesValidCoin_expect_coinAcceptedAndDisplayUpdated() {
        VendingMachine vendingMachine = new VendingMachine(new CoinValidator(0.25f, 0.05f));

        vendingMachine.accept(getNickel());

        assertEquals("$0.05", vendingMachine.display());
    }

    @Test
    public void when_vendingMachineReceivesInvalidCoin_expect_displayRemainUnchanged() {
        VendingMachine vendingMachine = new VendingMachine(new CoinValidator(0.25f, 0.05f));
        vendingMachine.accept(getPenny());
        assertEquals("INSERT COIN", vendingMachine.display());

        vendingMachine.accept(getNickel());
        vendingMachine.accept(getPenny());
        assertEquals("$0.05", vendingMachine.display());
    }

    @Test
    public void when_noCoinsInserted_expect_showInsertCoinOnDisplay() {
        VendingMachine vendingMachine = new VendingMachine(new CoinValidator(0.25f, 0.05f));

        assertEquals("INSERT COIN", vendingMachine.display());
    }

    @Test
    public void when_vendingMachineReceivesInvalidCoin_expect_coinReturnedInCoinReturn() {
        VendingMachine vendingMachine = new VendingMachine(new CoinValidator(0.25f, 0.05f));
        Coin pennyCoin1 = getPenny();
        Coin pennyCoin2 = getPenny();
        Coin nickelCoin = getNickel();

        vendingMachine.accept(pennyCoin1);
        vendingMachine.accept(nickelCoin);
        vendingMachine.accept(pennyCoin2);

        assertEquals("$0.05", vendingMachine.display());

        List<Coin> expectedReturnedCoins = List.of(pennyCoin1, pennyCoin2);
        assertEquals(expectedReturnedCoins, vendingMachine.coinReturn());
    }
}
