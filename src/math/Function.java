package math;

import math.DoubleToDouble;

public class Function {
    public static final DoubleToDouble sigmoid, rectifier, randomize;
    static {
        sigmoid = x -> 1 / (1 + Math.pow(Math.E, -x));
        rectifier = x -> Math.max(x, 0);
        randomize = x -> x * (2 * Math.random() - 1);
    }
}
