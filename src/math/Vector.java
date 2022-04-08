package math;

public class Vector extends Matrix {

    public final int size;

    public Vector(int size) {
        super(size, 1);
        this.size = size;
    }

    public double get(int loc) {
        return this.get(loc, 0);
    }
    public Vector set(int loc, double value) {
        this.set(loc, 0, value);
        return this;
    }
    public Vector set(double[] values) {
        this.cleanse(0);
        for(int i = 0; i < this.size && i < values.length; i++) {
            this.set(i, values[i]);
        }
        return this;
    }
    public Vector set(double[] values, int offset) {
        this.cleanse(0);
        for(int i = offset; i < values.length && i - offset < this.size; i++) {
            this.set(i - offset, values[offset]);
        }
        return this;
    }

    public static Vector add(Vector v1, Vector v2) {
        assert v1.size == v2.size;
        Vector temp = new Vector(v1.size);
        for(int i = 0; i < v1.size; i++) {
            temp.set(i, v1.get(i) + v2.get(i));
        }
        return temp;
    }
    public static Vector sub(Vector v1, Vector v2) {
        assert v1.size == v2.size;
        Vector temp = new Vector(v1.size);
        for(int i = 0; i < v1.size; i++) {
            temp.set(i, v1.get(i) - v2.get(i));
        }
        return temp;
    }
    public static Vector mult(Vector v, double d) {
        Vector temp = new Vector(v.size);
        for(int i = 0; i < v.size; i++) {
            temp.set(i, v.get(i) * d);
        }
        return temp;
    }
    public static Vector div(Vector v, double d) {
        Vector temp = new Vector(v.size);
        for(int i = 0; i < v.size; i++) {
            temp.set(i, v.get(i) / d);
        }
        return temp;
    }
    public static double dot(Vector v1, Vector v2) {
        assert v1.size == v2.size;
        double sum = 0;
        for(int i = 0; i < v1.size; i++) {
            sum += v1.get(i) * v2.get(i);
        }
        return sum;
    }
    public static double length(Vector v) {
        return Math.sqrt(dot(v, v));
    }
    public static Vector normalize(Vector v) {
        return div(v, length(v));
    }
    public static Vector cross(Vector v1, Vector v2) {
        assert v1.size == 3 && v2.size == 3;
        Vector temp = new Vector(3);
        temp.set(0, v1.get(1) * v2.get(2) - v1.get(2) * v2.get(1));
        temp.set(1, v1.get(2) * v2.get(0) - v1.get(0) * v2.get(2));
        temp.set(2, v1.get(0) * v2.get(1) - v1.get(1) * v2.get(0));
        return temp;
    }


    public Vector passFunction(DoubleToDouble function) {
        super.passFunction(function);
        return this;
    }
    public Vector duplicate() {
        return Vector.matrixToVector(super.duplicate());
    }
    public Vector cleanse(double value) {
        super.cleanse(value);
        return this;
    }
    public Vector randomize(double min, double max) {
        super.randomize(min, max);
        return this;
    }

    public static Vector matrixToVector(Matrix m) {
        assert m.columns == 1;
        Vector temp = new Vector(m.rows);
        for(int i = 0; i < temp.size; i++) {
            temp.set(i, m.get(i, 0));
        }
        return temp;
    }
    public double[] toArray() {
        double[] temp = new double[this.size];
        for(int i = 0; i < this.size; i++) {
            temp[i] = this.get(i);
        }
        return temp;
    }
    public String toString() {
        StringBuilder temp = new StringBuilder("<");
        for(int i = 0; i < this.size; i++) {
            temp.append(this.get(i)).append(", ");
        }
        temp.deleteCharAt(temp.length() - 1).deleteCharAt(temp.length() - 1).append(">");
        return temp.toString();
    }

}
