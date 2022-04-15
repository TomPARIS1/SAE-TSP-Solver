package SAE;

import java.util.ArrayList;
import java.util.List;

public class Fourmis {
    public List<Aretes> areteVisite;
    protected List<Villes> villeVisite;
    protected List<Villes> aVisiter;
    private Villes villeActuelle;
    private int compteur;

    public Fourmis (Villes villeActuelle, List<Villes> aVisite) {
        this.areteVisite = new ArrayList<>();
        this.villeVisite = new ArrayList<>();
        this.aVisiter = aVisite;
        this.compteur = 0;
        this.villeActuelle = villeActuelle;
    }

    public int FindIndex(int id) {
        for (int i = 0; i < aVisiter.size(); i++) {
            if (aVisiter.get(i).id == id) {
                return i;
            }
        }
        return 0;
    }

    public int FindIndexChemin(int id1,int id2,List<List<Aretes>> stock) {
        for (int i = 0; i < stock.size(); i++) {
            for (int j = 0; j < stock.size() - 1; j++) {
                if (stock.get(i).get(j).v1.id == id1 && stock.get(i).get(j).v2.id == id2) {
                    return i*10+j;
                }
            }
        }
        return 0;
    }

    public void choixChemin (List<List<Aretes>> stockAretes) {

        if (!villeVisite.contains(villeActuelle)) {
            villeVisite.add(villeActuelle);
            aVisiter.remove(this.FindIndex(villeActuelle.id));
        }

        if (this.aVisiter.size() == 0) {
            int id = this.FindIndexChemin(this.villeActuelle.id,0,stockAretes);
            Aretes chemin = stockAretes.get(id/10).get(id%10);
            this.areteVisite.add(chemin);
            this.compteur += chemin.distance;
            for (int i = 0; i < stockAretes.size(); i++) {
                for (int j = 0; j < stockAretes.size()-1; j++) {
                    if (!this.areteVisite.contains(stockAretes.get(i).get(j)) && stockAretes.get(i).get(j).getPheromone() > 0) {
                        stockAretes.get(i).get(j).retirePheromone();
                    }
                }
            }
        }

        if (aVisiter.size()>0) {

            int n = (int) (Math.random() * (aVisiter.size())-1);
            int destination = aVisiter.get(n).id;
            int depart = villeActuelle.id;

            int id = this.FindIndexChemin(depart,destination,stockAretes);
            Aretes chemin = stockAretes.get(id/10).get(id%10);

            if (Math.random() * (100) < chemin.visibilite) {

                areteVisite.add(chemin);
                villeActuelle = chemin.v2;

                    /*for (int i = 0; i < stockAretes.size(); i++) {
                        for (int j = 0; j < stockAretes.size() - 1; j++) {
                            if (stockAretes.get(i).get(j) == chemin) {
                                stockAretes.get(i).get(j).Pheromone();
                            }
                        }
                    }*/

                this.compteur += chemin.distance;
                this.choixChemin(stockAretes);
            } else {
                this.choixChemin(stockAretes);
            }
        }
    }


    @Override
    public String toString() {
        return "Fourmis{" +
                "areteVisite=" + areteVisite +
                ", villeVisite=" + villeVisite +
                ", villeActuelle=" + villeActuelle +
                ", compteur=" + compteur +
                '}';
    }
}


