package math;

import math.DoubleToDouble;

public class Function {
    public static final DoubleToDouble sigmoid, rectifier;
    static {
        sigmoid = x -> 1 / (1 + Math.pow(Math.E, -x));
        rectifier = x -> Math.max(x, 0);
    }
}
