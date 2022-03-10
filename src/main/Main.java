package main;

import math.Vector;

public class Main {

    public static void main(String[] args) {

        Vector v1 = new Vector(3).set(new double[] {3, -3, 1});
        Vector v2 = new Vector(3).set(new double[] {4, 9, 2});

        System.out.println(Vector.crossProduct(v1, v2));

    }

}
