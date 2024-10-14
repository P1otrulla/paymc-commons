package dev.piotrulla.paymc.commons.intrange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntRange {

    private final int min;
    private final int max;

    private List<Integer> soloRanges = new ArrayList<>();

    public IntRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public IntRange(int min, int max, List<Integer> soloRanges) {
        this.min = min;
        this.max = max;
        this.soloRanges = soloRanges;
    }

    public IntRange(int min, int max, Integer... soloRanges) {
        this.min = min;
        this.max = max;
        this.soloRanges = Arrays.stream(soloRanges).toList();
    }

    public int min() {
        return this.min;
    }

    public int max() {
        return this.max;
    }

    public List<Integer> soloRanges() {
        return this.soloRanges;
    }

    public boolean isInRange(int value) {
        return value >= this.min && value <= this.max;
    }
}
