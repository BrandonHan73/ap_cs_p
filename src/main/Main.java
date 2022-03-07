package main;

public class Main {

    public static void main(String[] args) {

        Matrix m1 = new Matrix(3, 4);
        m1.set(new double[][] {
                {3, 4, 5, 6},
                {1, 2, 6, 9},
                {9, 3, 6, 4},
        });

        Matrix m2 = new Matrix(4, 5);
        m2.set(new double[][] {
                {3, 4, 5, 6, 7},
                {1, 2, 2, 9, 5},
                {9, 3, 6, 4, 1},
                {6, 2, 5, 3 ,7},
        });

        System.out.println(Matrix.matrixMultiply(m1, m2));

    }

}
