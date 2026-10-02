package main;

import java.util.Scanner;
import java.util.Random;

import StatsArene.Combattant;
import StatsArene.Guerrier;
import StatsArene.Mage;
import StatsArene.Paladin;
import StatsArene.Tournoi;
import StatsArene.Voleur;

public class main {

	public static void lancerUnDe() {
		Scanner sc = new Scanner(System.in);
		String choix;
		System.out.print("Nombre de face entre 4 et 20 :");
		int nombre = sc.nextInt();
		if (nombre<4 || nombre>20) {
			lancerUnDe();
		}
		int hasard = new Random().nextInt(nombre) +1;
		System.out.print("Face obtenue : " + hasard);
	}

	public static void CalculerUnRang() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisis un nombre de points");
		int point = sc.nextInt();
		if (point<0) {
			System.out.println("Impossible");
			CalculerUnRang();
		}else if (point <100) {
			System.out.println("Bronze");
		}else if (point<499) {
			System.out.println("Argent");
		}else if (point <1499) {
			System.out.println("Or");
		}else {
			System.out.println("Légende");
		}
	}

	public static void TestCoupCritique() {
		int nbAttaque = 10000;
		double chanceCritique = 15;
		int nbCritique = 0;
		int compteur =0;
		int compteurPlusGrand = 1;
		Random random = new Random();
		for (int i = 0; i < nbAttaque; i++) {
	        int nombre = random.nextInt(100) + 1;

	        if (nombre <= chanceCritique) {
	            nbCritique++;
	            compteur++;
	            compteurPlusGrand = Math.max(compteurPlusGrand, compteur);
	        } else {
	            compteur = 0;
	        }
	    }
        System.out.println("Nombre d'attaques : " + nbAttaque);
        System.out.println("Nombre de critiques : " + nbCritique);
        System.out.println("Taux de critique de " + nbCritique*100/nbAttaque + "%");
        System.out.println("Serie de critique la plus longue : " + compteurPlusGrand);
	}

	public static Tournoi creerTournoi() {
		Tournoi t = new Tournoi();
		t.inscrire(new Guerrier("Kaelen", 20, 8, 120));
		t.inscrire(new Guerrier("Brakk", 20, 8, 120));
		t.inscrire(new Mage("Lyra", 20, 8, 120));
		t.inscrire(new Mage("Orin", 20, 8, 120));
		t.inscrire(new Voleur("Shade", 20, 8, 120, 20));
		t.inscrire(new Voleur("Nyx", 20, 8, 120, 30));
		t.inscrire(new Paladin("Aldric", 20, 8, 120));
		t.inscrire(new Paladin("Seraphine", 20, 8, 120));
		return t;
	}

	public static void tournoiComplet() {
		Tournoi t = creerTournoi();
		Combattant champion = t.lancer();

		System.out.println("\nChampion : " + champion);

		System.out.println("\n--- Classement ---");
		int rang = 1;
		for (Combattant c : t.classement()) {
			System.out.println(rang + ". " + c.getNom() + " (" + c.getClasse() + ") : "
					+ c.getVictoires() + " victoire(s)");
			rang++;
		}

		System.out.println();
		t.statsParClasse();
	}

	public static void simulation() {
		int nbTournois = 100;
		String[] classes = {"Guerrier", "Mage", "Voleur", "Paladin"};
		int[] titres = new int[classes.length];

		for (int n = 0; n < nbTournois; n++) {
			Tournoi t = creerTournoi();
			t.setAffichage(false);
			Combattant champion = t.lancer();
			for (int i = 0; i < classes.length; i++) {
				if (classes[i].equalsIgnoreCase(champion.getClasse())) {
					titres[i]++;
				}
			}
		}

		System.out.println("--- " + nbTournois + " tournois simulés ---");
		int meilleur = 0;
		for (int i = 0; i < classes.length; i++) {
			System.out.println(classes[i] + " : " + titres[i] + " titre(s)");
			if (titres[i] > titres[meilleur]) {
				meilleur = i;
			}
		}
		System.out.println("Classe la plus titrée : " + classes[meilleur]);
	}

	public static void menu() {
		Scanner sc = new Scanner(System.in);
	    String choix;
        do {
            System.out.println("////Arena Legends////");
            System.out.println("1. Lancer un dé");
            System.out.println("2. Calculer un rang");
            System.out.println("3. Test de coup critique");
            System.out.println("4. Tournoi complet");
            System.out.println("5. Simulation de 100 tournois");
            System.out.println("0. Quitter");
            choix = sc.next();

            switch (choix) {
                case "1" ->
                		lancerUnDe();
                case "2" ->
                		CalculerUnRang();
                case "3" ->
                		TestCoupCritique();
                case "4" ->
                		tournoiComplet();
                case "5" ->
                		simulation();
                case "0" ->
                		System.out.println("Au revoir !");
                default ->
                		System.out.println("Entrée invalide");
            }

        } while (!choix.equals("0"));
	}


	public static void main(String[] args) {
		menu();
	}

}