import org.example.CoinValidator;
import org.example.Products;
import org.example.VendingMachine;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectProductTest {

    CoinHelper coinHelper = new CoinHelper();

    @Test
    public given_enoughMoney_when_selectProduct_then_displayShowsThankYou() {
        CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);
        List<Products> availableProducts = List.of(Products.COLA, Products.CANDY, Products.CHIPS);
        VendingMachine vendingMachine = new VendingMachine(coinValidator, availableProducts);

        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals("THANK YOU", vendingMachine.display());
    }

    @Test
    public given_productBought_when_checkDisplay_then_displayShowInsertCoin() {
        CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);
        List<Products> availableProducts = List.of(Products.COLA, Products.CANDY, Products.CHIPS);
        VendingMachine vendingMachine = new VendingMachine(coinValidator, availableProducts);

        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.display();

        assertEquals("INSERT COIN", vendingMachine.display());
    }

    @Test
    public given_notEnoughMoney_when_selectProduct_then_displayShowPriceOfSelectedProduct() {
        CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);
        List<Products> availableProducts = List.of(Products.COLA, Products.CANDY, Products.CHIPS);
        VendingMachine vendingMachine = new VendingMachine(coinValidator, availableProducts);

        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals("$0.5", vendingMachine.display());
    }

    @Test
    public given_noMoneyInVendingMachine_when_checkDisplayAfterProductSelectFailed_then_showInsertCoin() {
        CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);
        List<Products> availableProducts = List.of(Products.COLA, Products.CANDY, Products.CHIPS);
        VendingMachine vendingMachine = new VendingMachine(coinValidator, availableProducts);

        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.display();

        assertEquals("INSERT COIN", vendingMachine.display());
    }

    @Test
    public given_tooLittleMoneyInVendingMachine_when_checkDisplayAfterProductSelectFailed_then_showCurrentAmountOfMoneyInVendingMachine() {
        CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);
        List<Products> availableProducts = List.of(Products.COLA, Products.CANDY, Products.CHIPS);
        VendingMachine vendingMachine = new VendingMachine(coinValidator, availableProducts);

        vendingMachine.accept(coinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.display();

        assertEquals("$0.25", vendingMachine.display());
    }
}
