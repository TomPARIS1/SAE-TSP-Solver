package SAE;

import java.util.ArrayList;
import java.util.List;

public class Fourmis {
    protected List<Aretes> areteVisite;
    protected List<Villes> villeVisite;
    protected List<Villes> stockVilles;
    protected List<Integer> nbAleatoire = new ArrayList<>();
    private Villes villeActuelle;
    private int compteur;

    public Fourmis (Villes villeActuelle, List<Villes> aVisite) {
        this.areteVisite = new ArrayList<>();
        this.villeVisite = new ArrayList<>();
        this.stockVilles = aVisite;
        this.compteur = 0;
        this.villeActuelle = villeActuelle;

        for (int i=1; i < aVisite.size(); i++) {
            this.nbAleatoire.add(i);
        }
    }

    public void choixChemin (List<List<Aretes>> stockAretes) {

        if (this.stockVilles.size() == 1) {

            this.areteVisite.add(stockAretes.get(this.villeActuelle.id).get(0));

            this.stockVilles.remove(0);

            for (int i = 0; i < stockAretes.size(); i++) {
                for (int j = 0; j < stockAretes.size()-1; j++) {
                    if (!this.areteVisite.contains(stockAretes.get(i).get(j)) && stockAretes.get(i).get(j).getPheromone() > 0) {
                        stockAretes.get(i).get(j).retirePheromone();
                    }
                }
            }

        }

        if (this.stockVilles.size() > 1) {
            System.out.println(this.stockVilles.size());

            while (true) {

                int n = (int) (Math.random() * (this.nbAleatoire.size()));
                int destination = this.nbAleatoire.get(n);
                int depart = this.villeActuelle.id;

                if (!this.villeVisite.contains(this.villeActuelle)) {
                    this.villeVisite.add(this.villeActuelle);
                }

                Aretes chemin = stockAretes.get(depart).get(destination - 1);

                if (Math.random() * (100) < chemin.visibilite) {
                    this.nbAleatoire.remove(n);

                    this.areteVisite.add(chemin);

                    this.stockVilles.remove(this.villeActuelle);
                    this.villeActuelle = chemin.v2;

                    for (int i = 0; i < stockAretes.size(); i++) {
                        for (int j = 0; j < stockAretes.size() - 1; j++) {
                            if (stockAretes.get(i).get(j) == chemin) {
                                stockAretes.get(i).get(j).Pheromone();
                            }
                        }
                    }

                    this.compteur += chemin.distance;
                    choixChemin(stockAretes);
                } else {
                    choixChemin(stockAretes);
                }
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


