package SAE;

import java.awt.desktop.OpenURIEvent;
import java.util.ArrayList;
import java.util.List;

public class Fourmis {
    protected List<Aretes> areteVisite;
    protected List<Villes> villeVisite;
    protected List<Villes> stockVilles;
    private Villes villeActuelle;
    private int compteur;

    public Fourmis (Villes villeActuelle, List<Villes> aVisite) {
        this.areteVisite = new ArrayList<>();
        this.villeVisite = new ArrayList<>();
        this.stockVilles = aVisite;
        this.compteur = 0;
        this.villeActuelle = villeActuelle;
    }

    public void choixChemin (List<List<Aretes>> stockAretes) {

        if (!this.stockVilles.isEmpty()) {
            int destination = (int) Math.random() * (stockAretes.size() - 2 - 2);

            int depart = villeActuelle.id;

            if (!this.villeVisite.contains(villeActuelle)) {
                this.villeVisite.add(villeActuelle);
            }

            Aretes chemin = stockAretes.get(depart).get(destination);

            if (this.stockVilles.size() == 1) {
                Villes v1 = this.villeVisite.get(villeVisite.size() - 1);

                this.areteVisite.add(stockAretes.get(v1.id).get(0));

                this.stockVilles.remove(0);

                System.out.println(this.compteur);
            }

            if (!this.villeVisite.contains(chemin.v2)) {
                if (Math.random() * (100 - 0) < chemin.visibilite) {
                    this.villeVisite.add(villeActuelle);
                    this.areteVisite.add(chemin);

                    this.stockVilles.remove(villeActuelle);
                    villeActuelle = chemin.v2;

                    this.compteur += chemin.distance;
                    choixChemin(stockAretes);
                } else {
                    choixChemin(stockAretes);
                }
            } else if (this.stockVilles.size() != 0) {
                System.out.println(this.stockVilles.size());
                choixChemin(stockAretes);
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


