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
        return this.access(row, column, false, 0);
    }
    public Matrix set(int row, int column, double value) {
        this.access(row, column, true, value);
        return this;
    }
    private synchronized double access(int row, int column, boolean edit, double value) {
        if(edit) {
            data[row][column] = value;
            return 0;
        } else {
            return data[row][column];
        }
    }

    public Matrix set(double[][] values) {
        this.cleanse(0);
        for(int r = 0; r < values.length && r < this.rows; r++) {
            for(int c = 0; c < values[r].length && c < this.columns; c++) {
                this.set(r, c, values[r][c]);
            }
        }
        return this;
    }
    public Matrix set(double[][] values, int rowOffset, int columnOffset) {
        this.cleanse(0);
        for(int r = Math.max(-rowOffset, 0); r < this.rows && r + rowOffset < values.length; r++) {
            for(int c = Math.max(-columnOffset, 0); c < this.columns && c + columnOffset < values[r + rowOffset].length; c++) {
                this.set(r, c, values[r + rowOffset][c + columnOffset]);
            }
        }
        return this;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public Matrix submatrix(int row, int column) {
        assert(0 < this.rows && 0 < this.columns && row < this.rows && column < this.columns);
        Matrix temp = new Matrix(this.rows - 1, this.columns - 1);
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < column; c++) {
                temp.set(r, c, this.get(r, c));
            }
            for(int c = column + 1; c < this.columns; c++) {
                temp.set(r, c - 1, this.get(r, c));
            }
        }
        for(int r = row + 1; r < this.rows; r++) {
            for(int c = 0; c < column; c++) {
                temp.set(r - 1, c, this.get(r, c));
            }
            for(int c = column + 1; c < this.columns; c++) {
                temp.set(r - 1, c - 1, this.get(r, c));
            }
        }
        return temp;
    }
    public double determinant() {
        assert(this.rows == this.columns);
        if(this.rows == 2) {
            return (this.get(0, 0) * this.get(1, 1)) - (this.get(1, 0) * this.get(0, 1));
        }
        double temp = 0;
        for(int c = 0; c < this.columns; c++) {
            temp += Math.pow(-1, c) * this.get(0, c) * this.submatrix(0, c).determinant();
        }
        return temp;
    }

    public static Matrix add(Matrix m1, Matrix m2) {
        assert m1.rows == m2.rows && m1.columns == m2.columns;
        Matrix temp = new Matrix(m1.rows, m1.columns);
        for(int r = 0; r < temp.rows; r++) {
            for(int c = 0; c < temp.columns; c++) {
                temp.set(r, c, m1.get(r, c) + m2.get(r, c));
            }
        }
        return temp;
    }
    public static Matrix subtract(Matrix m1, Matrix m2) {
        assert m1.rows == m2.rows && m1.columns == m2.columns;
        Matrix temp = new Matrix(m1.rows, m1.columns);
        for(int r = 0; r < temp.rows; r++) {
            for(int c = 0; c < temp.columns; c++) {
                temp.set(r, c, m1.get(r, c) - m2.get(r, c));
            }
        }
        return temp;
    }
    public static Matrix multiply(Matrix m1, Matrix m2) {
        assert m1.rows == m2.rows && m1.columns == m2.columns;
        Matrix temp = new Matrix(m1.rows, m1.columns);
        for(int r = 0; r < temp.rows; r++) {
            for(int c = 0; c < temp.columns; c++) {
                temp.set(r, c, m1.get(r, c) * m2.get(r, c));
            }
        }
        return temp;
    }
    public static Matrix divide(Matrix m1, Matrix m2) {
        assert m1.rows == m2.rows && m1.columns == m2.columns;
        Matrix temp = new Matrix(m1.rows, m1.columns);
        for(int r = 0; r < temp.rows; r++) {
            for(int c = 0; c < temp.columns; c++) {
                temp.set(r, c, m1.get(r, c) / m2.get(r, c));
            }
        }
        return temp;
    }
    public static Matrix matrixMultiply(Matrix m1, Matrix m2) {
        assert m1.columns == m2.rows;
        Matrix temp = new Matrix(m1.rows, m2. columns);
        for(int r = 0; r < temp.rows; r++) {
            for(int c = 0; c < temp.columns; c++) {
                for(int i = 0; i < m1.columns; i++) {
                    temp.set(r, c, temp.get(r, c) + m1.get(r, i) * m2.get(i, c));
                }
            }
        }
        return temp;
    }

    public Matrix cleanse(double value) {
        for(int r = 0; r < this.rows; r++) {
            for(int c = 0; c < this.columns; c++) {
                this.set(r, c, value);
            }
        }
        return this;
    }
    public String toString() {
        StringBuilder temp = new StringBuilder("[");
        for(int r = 0; r < this.rows; r++) {
            temp.append("(");
            for(int c = 0; c < this.columns; c++) {
                temp.append(this.get(r, c)).append(" ");
            }
            temp.deleteCharAt(temp.length() - 1).append(") ");
        }
        temp.deleteCharAt(temp.length() - 1).append("]");
        return temp.toString();
    }

}
