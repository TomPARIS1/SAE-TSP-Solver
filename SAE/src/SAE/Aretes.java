package SAE;

public class Aretes {
    public double visibilite;
    public double distance;
    public Villes v1;
    public Villes v2;
    public int pheromone;

    public Aretes (Villes v1, Villes v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public void setDistance () {
        this.distance = Math.sqrt((v2.coordX-v1.coordX)*(v2.coordX-v1.coordX)+(v2.coordY-v1.coordY)*(v2.coordY-v1.coordY));
    }

    public void setVisibilite (int zone) {
        this.visibilite = 100-(this.distance * 100) / zone;
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

    public static void main(String[] args) {
        Villes v1 = new Villes(20,0,0);
        Villes v2 = new Villes(100,0,1);

        Aretes A = new Aretes(v1,v2);

        A.setDistance();
        A.setVisibilite(100);

        System.out.println(A.visibilite);
    }
}
