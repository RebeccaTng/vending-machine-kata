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

    public DollarWrapper valueOf(String amountString) {
        if(amountString == null || amountString.isEmpty()) {
            throw new IllegalArgumentException("A monetary value is required");
        }

        if(amountString.startsWith("S")) {
            amountString = amountString.substring(amountString.indexOf("$") + 1);
        }

        return new DollarWrapper(new BigDecimal(amountString));
    }

    public DollarWrapper zero() {
        return new DollarWrapper(0);
    }

    public DollarWrapper addDollar(DollarWrapper amount) {
        return new DollarWrapper(value.add(amount.value));
    }

    public DollarWrapper subtractDollar(DollarWrapper amount) {
        return new DollarWrapper(value.subtract(amount.value));
    }

    public double divideByDouble(double amount) {
        return value.divide(BigDecimal.valueOf(amount), RoundingMode.HALF_DOWN).doubleValue();
    }

    public boolean greaterThanDollar(DollarWrapper amount) {
        return value.compareTo(amount.value) > 0;
    }

    public boolean greaterThanDouble(double amount) {
        return value.doubleValue() > amount;
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
