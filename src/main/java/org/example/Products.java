package org.example;

import org.example.money.DollarWrapper;

public enum Products {
    COLA(DollarWrapper.valueOf("$1.00")),
    CHIPS(DollarWrapper.valueOf("$0.50")),
    CANDY(DollarWrapper.valueOf("$0.65"));

    private final DollarWrapper price;

    Products(DollarWrapper price) {
        this.price = price;
    }

    public DollarWrapper getPrice() {
        return price;
    }
}
