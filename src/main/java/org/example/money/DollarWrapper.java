package org.example.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DollarWrapper {

    private BigDecimal value;

    public DollarWrapper(double value) {
        initValue(BigDecimal.valueOf(value));
    }

    public DollarWrapper(BigDecimal value) {
        initValue(value);
    }

    private void initValue(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_DOWN);
    }

    public static DollarWrapper valueOf(String amountString) {
        if(amountString == null || amountString.isEmpty()) {
            throw new IllegalArgumentException("A monetary value is required");
        }

        if(amountString.startsWith("$")) {
            amountString = amountString.substring(amountString.indexOf("$") + 1);
        }

        return new DollarWrapper(new BigDecimal(amountString).setScale(2, RoundingMode.HALF_DOWN));
    }

    public static DollarWrapper zero() {
        return new DollarWrapper(0);
    }

    public DollarWrapper add(DollarWrapper amount) {
        return new DollarWrapper(value.add(amount.value));
    }

    public DollarWrapper subtract(DollarWrapper amount) {
        return new DollarWrapper(value.subtract(amount.value));    }

    public double dividedBy(DollarWrapper amount) {
        return value.divide(amount.value, RoundingMode.HALF_DOWN).doubleValue();
    }

    public boolean greaterThan(double amount) {
        return value.doubleValue() > amount;
    }

    public boolean greaterThanOrEquals(DollarWrapper amount) {
        return value.compareTo(amount.value) >= 0;
    }

    public boolean equalsTo(double amount) {
        return value.doubleValue() == amount;
    }

    public double asDouble() {
        return value.doubleValue();
    }

    public BigDecimal asBigDecimal() {
        return value;
    }

    public String asDollarString() {
        return "$" + value;
    }
}
