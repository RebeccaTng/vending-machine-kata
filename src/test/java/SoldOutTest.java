import helpers.CoinHelper;
import helpers.VendingMachineHelper;
import org.example.Products;
import org.example.VendingMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoldOutTest {

    @Test
    public void given_itemOutOfStock_when_selectItem_then_displaySoldOut() {

        VendingMachine vendingMachine = VendingMachineHelper.getOutOfStockVendingMachine();

        vendingMachine.selectProduct(Products.COLA);

        assertEquals("SOLD OUT", vendingMachine.seeDisplay());
    }

    @Test
    public void given_moneyInVendingMachine_when_checkDisplayAfterSoldOut_then_displayInsertCoin() {
        VendingMachine vendingMachine = VendingMachineHelper.getOutOfStockVendingMachine();

        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.selectProduct(Products.COLA);
        vendingMachine.seeDisplay();

        assertEquals("$0.25", vendingMachine.seeDisplay());
    }

    @Test
    public void given_noMoneyInVendingMachine_when_checkDisplayAfterSoldOut_then_displayInsertCoin() {
        VendingMachine vendingMachine = VendingMachineHelper.getOutOfStockVendingMachine();

        vendingMachine.insert(CoinHelper.getQuarter());
        vendingMachine.selectProduct(Products.COLA);
        vendingMachine.seeDisplay();

        assertEquals("INSERT COIN", vendingMachine.seeDisplay());
    }
}
