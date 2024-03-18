import org.example.CoinValidator;
import org.example.Products;
import org.example.VendingMachine;
import org.example.money.DollarWrapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectProductTest {

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
    public void given_enoughMoney_when_selectProduct_then_displayShowsThankYou() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals("THANK YOU", vendingMachine.seeDisplay());
    }

    @Test
    public void given_productBought_when_checkDisplay_then_displayShowInsertCoin() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.seeDisplay();

        assertEquals("INSERT COIN", vendingMachine.seeDisplay());
    }

    @Test
    public void given_notEnoughMoney_when_selectProduct_then_displayShowPriceOfSelectedProduct() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals("$0.50", vendingMachine.seeDisplay());
    }

    @Test
    public void given_noMoneyInVendingMachine_when_checkDisplayAfterProductSelectFailed_then_showInsertCoin() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.seeDisplay();

        assertEquals("INSERT COIN", vendingMachine.seeDisplay());
    }

    @Test
    public void given_tooLittleMoneyInVendingMachine_when_checkDisplayAfterProductSelectFailed_then_showCurrentAmountOfMoneyInVendingMachine() {
        VendingMachine vendingMachine = getBasicVendingMachine();

        vendingMachine.insert(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.seeDisplay();

        assertEquals("$0.25", vendingMachine.seeDisplay());
    }
}
