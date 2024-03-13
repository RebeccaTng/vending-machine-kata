package org.example;

public enum Products {
    COLA(1.0f),
    CHIPS(0.5f),
    CANDY(0.65f);

    private final float price;

    Products(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}
