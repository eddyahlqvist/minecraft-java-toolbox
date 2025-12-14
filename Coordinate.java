public class Coordinate {
    private final double x;
    private final double z;

    public Coordinate (double x, double z) {
        this.x = x;
        this.z = z;
    }

    public Coordinate toNether() {
        return new Coordinate(
                Math.floor(this.x / 8),
                Math.floor(this.z / 8)
        );
    }

    public Coordinate toOverworld() {
        return new Coordinate(
                this.x * 8,
                this.z * 8
        );
    }

    @Override
    public String toString() {
        return "(" + x + ", " + z + ")";
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

}