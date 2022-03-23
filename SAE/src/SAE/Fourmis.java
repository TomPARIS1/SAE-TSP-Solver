package SAE;

import java.awt.desktop.OpenURIEvent;
import java.util.ArrayList;
import java.util.List;

public class Fourmis {
    public List<Aretes> areteVisite;
    public List<Villes> villeVisite;
    private Villes villeActuelle;
    private int compteur;

    public Fourmis (Villes villeActuelle) {
        this.areteVisite = new ArrayList<>();
        this.villeVisite = new ArrayList<>();
        this.compteur = 0;
        this.villeActuelle = villeActuelle;
    }

    public void choixChemin (List<List<Aretes>> stockAretes, List<Villes> stockVilles) {

        if (!stockVilles.isEmpty()) {

            int destination = (int) Math.random() * (stockAretes.size() - 2 - 2);

            int depart = villeActuelle.id;

            Aretes chemin = stockAretes.get(depart).get(destination);

            if (!this.villeVisite.contains(chemin.v2)) {
                if (Math.random() * (100 - 0) < chemin.visibilite) {
                    this.villeVisite.add(villeActuelle);
                    this.areteVisite.add(chemin);

                    villeActuelle = chemin.v2;
                    stockVilles.remove(villeActuelle);

                    this.compteur += chemin.distance;
                } else {
                    choixChemin(stockAretes, stockVilles);
                }
            }
            else {
                choixChemin(stockAretes, stockVilles);
            }
        }

        Villes v1 = this.villeVisite.get(villeVisite.size()-1);

        this.areteVisite.add(stockAretes.get(v1.id).get(0));

        System.out.println(this.villeVisite);
        System.out.println(this.areteVisite);
        System.out.println(this.compteur);
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


