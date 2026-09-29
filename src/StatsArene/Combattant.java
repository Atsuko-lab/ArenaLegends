package StatsArene;

public class Combattant {

	private String nom;
	private int pvMax;
	private int pv;
	private int attaque;
	private int defense;
	private static int nbCombattants;

	public Combattant(String nom, int attaque, int defense, int pvMax) {
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("nom ne doit pas être vide");
		}
		verifierBornes("attaque", attaque, 5, 50);
		verifierBornes("defense", defense, 0, 30);
		verifierBornes("pvMax", pvMax, 50, 300);

		this.nom = nom;
		this.attaque = attaque;
		this.defense = defense;
		this.pvMax = pvMax;
		this.pv = pvMax;
		nbCombattants++; 
	}

	private static void verifierBornes(String champ, int valeur, int min, int max) {
		if (valeur < min || valeur > max) {
			throw new IllegalArgumentException(
				champ + " doit être entre " + min + " et " + max + ", reçu : " + valeur);
		}
	}

	public static void main(String[] args) {
		Combattant a = new Combattant("Kai", 30, 10, 100);
		System.out.println("Créé : " + a.nom + ", combattants : " + nbCombattants);

		new Combattant("Rex", 70, 10, 100);
	}
}