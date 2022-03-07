package main;

public class Main {

    public static void main(String[] args) {

        Matrix m = new Matrix(4, 5);
        m.set(new double[][] {
                {3, 4, 5, 6, 7},
                {1, 2},
                {9, 3, 6, 4},
                {6, 2, 5, 3 ,7, 4},
                {3, 5, 2, 1},
                {9, 2, 5}
        }, -1, -1);

        System.out.println(m);

    }

}
