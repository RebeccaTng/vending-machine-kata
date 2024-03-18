import helpers.CoinHelper;
import helpers.VendingMachineHelper;
import org.example.Products;
import org.example.VendingMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectProductTest {

    @Test
    public void given_enoughMoney_when_selectProduct_then_displayShowsThankYou() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();

        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals("THANK YOU", vendingMachine.seeDisplay());
    }

    @Test
    public void given_productBought_when_checkDisplay_then_displayShowInsertCoin() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();

        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.seeDisplay();

        assertEquals("INSERT COIN", vendingMachine.seeDisplay());
    }

    @Test
    public void given_notEnoughMoney_when_selectProduct_then_displayShowPriceOfSelectedProduct() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();

        vendingMachine.selectProduct(Products.CHIPS);

        assertEquals("$0.50", vendingMachine.seeDisplay());
    }

    @Test
    public void given_noMoneyInVendingMachine_when_checkDisplayAfterProductSelectFailed_then_showInsertCoin() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();

        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.seeDisplay();

        assertEquals("INSERT COIN", vendingMachine.seeDisplay());
    }

    @Test
    public void given_tooLittleMoneyInVendingMachine_when_checkDisplayAfterProductSelectFailed_then_showCurrentAmountOfMoneyInVendingMachine() {
        VendingMachine vendingMachine = VendingMachineHelper.getStockedVendingMachine();

        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.selectProduct(Products.CHIPS);
        vendingMachine.seeDisplay();

        assertEquals("$0.25", vendingMachine.seeDisplay());
    }
}
