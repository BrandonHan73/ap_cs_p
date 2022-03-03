package main;

public class Matrix {

    private double[][] data;
    public final int rows, columns;

    public Matrix(int rows, int columns) {
        data = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.cleanse(0);
    }

    public double get(int row, int column) {
        return access(row, column, false, 0);
    }
    public double set(int row, int column, double value) {
        return access(row, column, true, value);
    }
    private synchronized double access(int row, int column, boolean edit, double value) {
        if(edit) {
            double temp = data[row][column];
            data[row][column] = value;
            return temp;
        } else {
            return data[row][column];
        }
    }

    public Matrix cleanse(double value) {
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < columns; c++) {
                set(r, c, value);
            }
        }
        return this;
    }

}
