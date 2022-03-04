package main;

public class Main {

    public static void main(String[] args) {

        double[][] val = {{4, 3, 2, 2}, {0, 1, -3, 3}, {0, -1, 3, 3}, {0, 3, 1, 1}};
        Matrix m = new Matrix(4, 4);
        for(int r = 0; r < 4; r++) {
            for(int c = 0; c < 4; c++) {
                m.set(r, c, val[r][c]);
            }
        }

        System.out.println(m);
        System.out.println(m.determinant());

    }

}
