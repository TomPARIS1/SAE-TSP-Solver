package SAE;

public class Villes {
    public double coordX;
    public double coordY;
    public int id;

    public Villes (double coordX, double coordY, int id) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Villes{" +
                "coordX=" + coordX +
                ", coordY=" + coordY +
                ", id=" + id +
                '}';
    }
}
