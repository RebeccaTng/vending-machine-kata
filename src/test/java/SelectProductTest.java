import org.example.CoinValidator;
import org.example.Products;
import org.example.VendingMachine;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectProductTest {

    CoinHelper coinHelper = new CoinHelper();

    private VendingMachine getBasicVendingMachine() {
        CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);
        Map<Products, Integer> availableProducts = Map.ofEntries(
                Map.entry(Products.COLA, 10),
                Map.entry(Products.CANDY, 5),
                Map.entry(Products.CHIPS, 1)
        );
        return new VendingMachine(coinValidator, availableProducts);
    }

    @Test
    public given_enoughMoney_when_selectProduct_then_displayShowsThankYou() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals("THANK YOU", vendingMachine.display());
    }

    @Test
    public given_productBought_when_checkDisplay_then_displayShowInsertCoin() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.display();

        assertEquals("INSERT COIN", vendingMachine.display());
    }

    @Test
    public given_notEnoughMoney_when_selectProduct_then_displayShowPriceOfSelectedProduct() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals("$0.5", vendingMachine.display());
    }

    @Test
    public given_noMoneyInVendingMachine_when_checkDisplayAfterProductSelectFailed_then_showInsertCoin() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.display();

        assertEquals("INSERT COIN", vendingMachine.display());
    }

    @Test
    public given_tooLittleMoneyInVendingMachine_when_checkDisplayAfterProductSelectFailed_then_showCurrentAmountOfMoneyInVendingMachine() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.display();

        assertEquals("$0.25", vendingMachine.display());
    }
}
