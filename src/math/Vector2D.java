package math;

public class Vector2D extends Vector {

    public Vector2D(double x, double y) {
        super(2);
        this.set(0, x);
        this.set(1, y);
    }

    public Vector2D() {
        super(2);
    }

    public Vector2D(Vector2D v) {
        super(2);
        this.set(0, v.get(0));
        this.set(1, v.get(1));
    }

    public double getX() {
        return this.get(0);
    }

    public double getY() {
        return this.get(1);
    }

}
