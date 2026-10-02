package StatsArene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Tournoi {

    private static final int MAX_INSCRITS = 8;
    private static final int MAX_TOURS = 50;

    private ArrayList<Combattant> participants = new ArrayList<>();
    private boolean affichage = true;

    // false = tournoi silencieux (pour la simulation des 100 tournois)
    public void setAffichage(boolean affichage) {
        this.affichage = affichage;
    }

    private void afficher(String message) {
        if (affichage) {
            System.out.println(message);
        }
    }

    // 1. Inscription : pas de doublon de nom (insensible à la casse), 8 inscrits max
    public boolean inscrire(Combattant c) {
        if (c == null || participants.size() >= MAX_INSCRITS) {
            return false;
        }
        for (Combattant p : participants) {
            if (p.getNom().equalsIgnoreCase(c.getNom())) {
                return false;
            }
        }
        participants.add(c);
        return true;
    }

    // 2. Désinscription : Iterator pour éviter la ConcurrentModificationException
    public boolean desinscrire(String nom) {
        Iterator<Combattant> it = participants.iterator();
        while (it.hasNext()) {
            if (it.next().getNom().equalsIgnoreCase(nom)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    // 3. Duel : celui qui a la meilleure attaque commence, 50 tours max
    public Combattant duel(Combattant a, Combattant b) {
        Combattant attaquant = (a.getAttaque() >= b.getAttaque()) ? a : b;
        Combattant defenseur = (attaquant == a) ? b : a;

        afficher("=== DUEL : " + a.getNom() + " vs " + b.getNom() + " ===");

        int tour = 1;
        while (tour <= MAX_TOURS && !a.estKO() && !b.estKO()) {
            int degats = attaquant.attaquer(defenseur);
            afficher("Tour " + tour + " : " + attaquant.getNom() + " attaque "
                    + defenseur.getNom() + " (" + degats + " dégâts) -> " + defenseur.getNom() + " : "
                    + defenseur.getPv() + "/" + defenseur.getPvMax() + " PV");

            // on inverse les rôles
            Combattant tmp = attaquant;
            attaquant = defenseur;
            defenseur = tmp;
            tour++;
        }

        Combattant vainqueur;
        if (a.estKO()) {
            vainqueur = b;
        } else if (b.estKO()) {
            vainqueur = a;
        } else {
            // limite de tours atteinte : meilleur pourcentage de PV
            double pctA = (double) a.getPv() / a.getPvMax();
            double pctB = (double) b.getPv() / b.getPvMax();
            afficher("Limite de " + MAX_TOURS + " tours atteinte : décision aux PV restants.");
            vainqueur = (pctA >= pctB) ? a : b;
        }

        vainqueur.ajouterVictoire();
        afficher(">>> Vainqueur : " + vainqueur.getNom() + "\n");
        return vainqueur;
    }

    // 4. Tournoi à élimination directe
    public Combattant lancer() {
        if (participants.size() < 2) {
            afficher("Il faut au moins 2 participants pour lancer le tournoi.");
            return null;
        }

        // copie mélangée : la liste des inscrits reste intacte pour le classement
        ArrayList<Combattant> enLice = new ArrayList<>(participants);
        Collections.shuffle(enLice);

        int manche = 1;
        while (enLice.size() > 1) {
            afficher("########## MANCHE " + manche + " ##########");
            ArrayList<Combattant> vainqueurs = new ArrayList<>();

            for (int i = 0; i + 1 < enLice.size(); i += 2) {
                vainqueurs.add(duel(enLice.get(i), enLice.get(i + 1)));
            }

            // nombre impair : le dernier passe directement à la manche suivante
            if (enLice.size() % 2 == 1) {
                Combattant exempte = enLice.get(enLice.size() - 1);
                afficher(exempte.getNom() + " est exempté de cette manche.\n");
                vainqueurs.add(exempte);
            }

            // soin complet des vainqueurs
            // (un vainqueur n'est jamais K.O., donc la règle "on ne soigne pas un K.O." ne bloque pas)
            for (Combattant v : vainqueurs) {
                v.soigner(v.getPvMax());
            }

            enLice = vainqueurs;
            manche++;
        }

        Combattant champion = enLice.get(0);
        afficher("***** CHAMPION : " + champion.getNom() + " *****");
        return champion;
    }

    // 5. Classement par victoires décroissantes (tri par sélection, fait main)
    public ArrayList<Combattant> classement() {
        ArrayList<Combattant> tri = new ArrayList<>(participants);

        for (int i = 0; i < tri.size() - 1; i++) {
            int max = i;
            for (int j = i + 1; j < tri.size(); j++) {
                if (tri.get(j).getVictoires() > tri.get(max).getVictoires()) {
                    max = j;
                }
            }
            if (max != i) {
                Combattant tmp = tri.get(i);
                tri.set(i, tri.get(max));
                tri.set(max, tmp);
            }
        }
        return tri;
    }

    // 6. Victoires cumulées par classe, via getClasse() (pas d'instanceof)
    public void statsParClasse() {
        String[] classes = {"Guerrier", "Mage", "Voleur", "Paladin"};
        int[] victoires = new int[classes.length];

        for (Combattant c : participants) {
            for (int i = 0; i < classes.length; i++) {
                if (classes[i].equalsIgnoreCase(c.getClasse())) {
                    victoires[i] += c.getVictoires();
                }
            }
        }

        System.out.println("--- Victoires par classe ---");
        for (int i = 0; i < classes.length; i++) {
            System.out.println(classes[i] + " : " + victoires[i]);
        }
    }
}