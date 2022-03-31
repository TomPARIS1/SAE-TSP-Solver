package SAE;

import java.awt.desktop.OpenURIEvent;
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

        if (!this.stockVilles.isEmpty()) {
            System.out.println(this.stockVilles.size());

            int n = (int) (Math.random() * (this.nbAleatoire.size()));
            int destination = this.nbAleatoire.get(n);

            if (this.nbAleatoire.contains(destination)) {
                this.nbAleatoire.remove(this.nbAleatoire.get(n)); // Ne marche pas en mettant "destination" ?????

                int depart = this.villeActuelle.id;

                if (!this.villeVisite.contains(this.villeActuelle)) {
                    this.villeVisite.add(this.villeActuelle);
                }

                n = destination-1;
                Aretes chemin = stockAretes.get(depart).get(n);

                if (this.stockVilles.size() == 1) {
                    Villes v1 = this.villeVisite.get(this.villeVisite.size() - 1);

                    this.areteVisite.add(stockAretes.get(v1.id).get(0));

                    this.stockVilles.remove(0);

                    System.out.println(this.compteur);
                }

                if (!this.villeVisite.contains(chemin.v2)) {
                    if (Math.random() * (100) < chemin.visibilite) {
                        this.areteVisite.add(chemin);

                        this.stockVilles.remove(this.villeActuelle);
                        this.villeActuelle = chemin.v2;

                        this.compteur += chemin.distance;
                        choixChemin(stockAretes);
                    } else {
                        this.nbAleatoire.add(destination);
                        choixChemin(stockAretes);
                    }
                } else if (this.stockVilles.size() != 0) {
                    this.nbAleatoire.add(destination);
                    this.villeVisite.remove(this.villeActuelle);
                    choixChemin(stockAretes);
                }
            }
            else {
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


