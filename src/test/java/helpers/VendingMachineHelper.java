package helpers;

import org.example.CoinValidator;
import org.example.Products;
import org.example.VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineHelper {

    public static VendingMachine getStockedVendingMachine() {
        CoinValidator coinValidator = new CoinValidator(0.25f, 0.05f);
        Map<Products, Integer> availableProducts = new HashMap<>(Map.ofEntries(
                Map.entry(Products.COLA, 10),
                Map.entry(Products.CANDY, 5),
                Map.entry(Products.CHIPS, 1)
        ));
        return new VendingMachine(coinValidator, availableProducts);
    }

    public static VendingMachine getStockedVendingMachine(CoinValidator coinValidator) {
        Map<Products, Integer> availableProducts = new HashMap<>(Map.ofEntries(
                Map.entry(Products.COLA, 10),
                Map.entry(Products.CANDY, 5),
                Map.entry(Products.CHIPS, 1)
        ));
        return new VendingMachine(coinValidator, availableProducts);
    }
}
