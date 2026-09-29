package StatsArene;

import java.util.Random;

public class StatsArene {

    public static double moyenne(int[] t) {
        int somme = 0;
        for (int i = 0; i < t.length; i++) {
            somme += t[i];
        }
        return somme / t.length;
    }

    public static int max(int[] t) {
        int max = t[0];
        for (int i = 1; i < t.length; i++) {
            if (t[i] > max) {
                max = t[i];
            }
        }
        return max;
    }

    public static int min(int[] t) {
        int min = t[0];
        for (int i = 1; i < t.length; i++) {
            if (t[i] < min) {
                min = t[i];
            }
        }
        return min;
    }

    public static int trierDecroissant(int[] t) {
        int echanges = 0;
        boolean aEchange = true;
        int fin = t.length - 1;

        while (aEchange) {
            aEchange = false;
            for (int i = 0; i < fin; i++) {
                if (t[i] < t[i + 1]) {
                    int tmp = t[i];
                    t[i] = t[i + 1];
                    t[i + 1] = tmp;
                    echanges++;
                    aEchange = true;
                }
            }
        }
        return echanges;
    }

    public static int[] sansDoublons(int[] t) {

        int[] temp = new int[t.length];
        int taille = 0;

        for (int i = 0; i < t.length; i++) {
            boolean dejaVu = false;
            for (int j = 0; j < taille; j++) {
                if (temp[j] == t[i]) {
                    dejaVu = true;
                    break;
                }
            }
            if (!dejaVu) {
                temp[taille] = t[i];
                taille++;
            }
        }

        int[] resultat = new int[taille];
        for (int i = 0; i < taille; i++) {
            resultat[i] = temp[i];
        }
        return resultat;
    }

    public static char[][] creerArene(int nbObstacles, Random rnd) {
        char[][] grille = new char[8][8];
        for (int l = 0; l < grille.length; l++) {
            for (int c = 0; c < grille[l].length; c++) {
                grille[l][c] = '.';
            }
        }
        for (int i = 0; i < nbObstacles; i++) {
            placer(grille, '#', rnd);
        }
        placer(grille, 'A', rnd);
        placer(grille, 'B', rnd);
        return grille;
    }

    private static void placer(char[][] grille, char symbole, Random rnd) {
        int l, c;
        do {
            l = rnd.nextInt(grille.length);
            c = rnd.nextInt(grille[0].length);
        } while (grille[l][c] != '.');
        grille[l][c] = symbole;
    }

    public static void afficherGrille(char[][] grille) {
        System.out.print("   ");
        for (int c = 0; c < grille[0].length; c++) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (int l = 0; l < grille.length; l++) {
            System.out.print(l + "  ");
            for (int c = 0; c < grille[l].length; c++) {
                System.out.print(grille[l][c] + " ");
            }
            System.out.println();
        }
    }


    public static int distance(char[][] grille) {
        int la = -1, ca = -1, lb = -1, cb = -1;

        for (int l = 0; l < grille.length; l++) {
            for (int c = 0; c < grille[l].length; c++) {
                if (grille[l][c] == 'A') {
                    la = l;
                    ca = c;
                } else if (grille[l][c] == 'B') {
                    lb = l;
                    cb = c;
                }
            }
        }

        if (la == -1 || lb == -1) {
            return -1;
        }
        return abs(la - lb) + abs(ca - cb);
    }

    private static int abs(int x) {
        return x < 0 ? -x : x;
    }


    public static void afficher(int[] t) {
        System.out.print("[");
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i]);
            if (i < t.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }


    public static void main(String[] args) {
        int[] scores = {42, 87, 15, 99, 63, 87, 5, 71, 99, 34, 50, 28};

        System.out.println("1. Statistiques");
        System.out.println("Moyenne : " + moyenne(scores));
        System.out.println("Max     : " + max(scores));
        System.out.println("Min     : " + min(scores));

        System.out.println("\n3. Sans doublons");
        int[] uniques = sansDoublons(scores);
        System.out.print("Résultat (" + uniques.length + " cases) : ");
        afficher(uniques);
        System.out.print("Original intact : ");
        afficher(scores);

        System.out.println("\n 2. Tri décroissant");
        int[] copie = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            copie[i] = scores[i];
        }
        int nbEchanges = trierDecroissant(copie);
        afficher(copie);
        System.out.println("Nombre d'échanges : " + nbEchanges);

        System.out.println("\n 4. Arène");
        char[][] arene = creerArene(6, new Random());
        afficherGrille(arene);

        System.out.println("\n=== 5. Distance A-B ===");
        System.out.println("Distance de Manhattan : " + distance(arene));
    }
}