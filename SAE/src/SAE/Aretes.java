package SAE;

public class Aretes {
    public double visibilite;
    public double distance;
    public Villes v1;
    public Villes v2;
    public int pheromone = 0;

    public Aretes (Villes v1, Villes v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public void setDistance () {
        this.distance = Math.sqrt((v2.coordX-v1.coordX)*(v2.coordX-v1.coordX)+(v2.coordY-v1.coordY)*(v2.coordY-v1.coordY));
    }

    public void setVisibilite (int zone) {
        this.visibilite = 100-(this.distance * 100) / zone + this.pheromone/10;

    }

    public double getVisibilite () {
        return this.visibilite;
    }

    public int getPheromone() {
        return pheromone;
    }

    public void Pheromone () {
        this.pheromone += 50;
    }

    public void retirePheromone () {
        this.pheromone -= 10;
    }

    @Override
    public String toString() {
        return "Aretes{" +
                "visibilite=" + visibilite +
                ", distance=" + distance +
                ", v1=" + v1 +
                ", v2=" + v2 +
                '}';
    }
}
