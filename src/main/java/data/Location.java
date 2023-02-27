package data;

public class Location {
    private Double x; //Поле не может быть null
    private Float y; //Поле не может быть null
    private long z;

    public Location(Double x, Float y, long z) {
        if (x == null || y == null) throw new IllegalArgumentException("x and y coordinates can't be null");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(Double x, Float y) {
        if (x == null || y == null) throw new IllegalArgumentException("x and y coordinates can't be null");
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public long getZ() {
        return z;
    }

    public void setZ(long z) {
        this.z = z;
    }
}
