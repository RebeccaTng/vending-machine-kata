import org.example.Coin;
import org.example.VendingMachine;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptCoinsTest {

    public Coin getPenny() {
        return new Coin(2.5f, 0.75f);
    }

    public Coin getNickel() {
        return new Coin(5f, 0.84f);
    }

    @Test
    public void machineShouldAcceptValidCoins() {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.accept(getNickel());

        assertEquals("$0.05", vendingMachine.display());
    }

    @Test
    public void machineShouldRejectInvalidCoins() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.accept(getPenny());
        assertEquals("$0.0", vendingMachine.display());

        vendingMachine.accept(getNickel());
        vendingMachine.accept(getPenny());
        assertEquals("$0.05", vendingMachine.display());
    }

    @Test
    public void machineShouldDisplayNoCoinsWhenNoCoinsInserted() {
        VendingMachine vendingMachine = new VendingMachine();

        assertEquals("INSERT COIN", vendingMachine.display());
    }

    @Test
    public void rejectedCoinsShouldBeInCoinReturn() {
        VendingMachine vendingMachine = new VendingMachine();
        Coin pennyCoin1 = getPenny();
        Coin pennyCoin2 = getPenny();
        Coin nickelCoin = getNickel();

        vendingMachine.accept(pennyCoin1);
        vendingMachine.accept(nickelCoin);
        vendingMachine.accept(pennyCoin2);

        assertEquals("$0.0", vendingMachine.display());

        List<Coin> expectedReturnedCoins = List.of(pennyCoin1, pennyCoin2);
        assertEquals(expectedReturnedCoins, vendingMachine.coinReturn());
    }
}
