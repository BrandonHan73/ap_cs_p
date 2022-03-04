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

    @SuppressWarnings("SpellCheckingInspection")
    public Matrix submatrix(int row, int column) {
        assert(0 < rows && 0 < columns && row < rows && column < columns);
        Matrix temp = new Matrix(rows - 1, columns - 1);
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < column; c++) {
                temp.set(r, c, get(r, c));
            }
            for(int c = column + 1; c < columns; c++) {
                temp.set(r, c - 1, get(r, c));
            }
        }
        for(int r = row + 1; r < rows; r++) {
            for(int c = 0; c < column; c++) {
                temp.set(r - 1, c, get(r, c));
            }
            for(int c = column + 1; c < columns; c++) {
                temp.set(r - 1, c - 1, get(r, c));
            }
        }
        return temp;
    }

    public double determinant() {
        assert(rows == columns);
        if(rows == 2) {
            return (get(0, 0) * get(1, 1)) - (get(1, 0) * get(0, 1));
        }
        double temp = 0;
        for(int c = 0; c < columns; c++) {
            temp += Math.pow(-1, c) * get(0, c) * submatrix(0, c).determinant();
        }
        return temp;
    }

    public Matrix cleanse(double value) {
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < columns; c++) {
                set(r, c, value);
            }
        }
        return this;
    }

    public String toString() {
        StringBuilder temp = new StringBuilder("[");
        for(int r = 0; r < rows; r++) {
            temp.append("(");
            for(int c = 0; c < columns; c++) {
                temp.append(get(r, c)).append(" ");
            }
            temp.deleteCharAt(temp.length() - 1).append(") ");
        }
        temp.deleteCharAt(temp.length() - 1).append("]");
        return temp.toString();
    }

}
