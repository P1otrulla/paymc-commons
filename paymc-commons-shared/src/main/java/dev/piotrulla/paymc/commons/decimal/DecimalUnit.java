package dev.piotrulla.paymc.commons.decimal;

import java.io.Serializable;

public class DecimalUnit implements Serializable {

    private final long factor;
    private final String suffix;

    public DecimalUnit(long factor, String suffix) {
        this.factor = factor;
        this.suffix = suffix;
    }

    public long getFactor() {
        return this.factor;
    }

    public String getSuffix() {
        return this.suffix;
    }

}
