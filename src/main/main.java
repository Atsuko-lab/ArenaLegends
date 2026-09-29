package main;
import java.util.Scanner;
import java.util.Random;

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
	
	

	public static void menu() {
		Scanner sc = new Scanner(System.in);
	    String choix;
        do {
            System.out.println("////Arena Legends////");
            System.out.println("1. Lancer un dé");
            System.out.println("2. Calculer un rang");
            System.out.println("3. Test de coup critique");
            System.out.println("0. Quitter");
            choix = sc.next();

            switch (choix) {
                case "1" -> 
                		lancerUnDe();
                case "2" -> 
                		CalculerUnRang();
                case "3" -> 
                		TestCoupCritique();
                case "0" -> 
                		System.out.println("Au revoir !");
                default -> 
                		System.out.println("Entrée invalide");
            }

        } while (!choix.equals("0"));
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
	}

}
