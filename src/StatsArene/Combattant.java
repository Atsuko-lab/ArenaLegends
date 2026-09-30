package StatsArene;

public abstract class Combattant {

	private String nom;
	private int pvMax;
	private int pv;
	private int attaque;
	private int defense;
	private static int nbCombattants;

    public abstract int attaquer(Combattant cible);
	
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

	public String getNom() {
		return nom;
	}

	public int getPv() {
		return pv;
	}

	public int getPvMax() {
		return pvMax;
	}

	public int getAttaque() {
		return attaque;
	}

	public int getDefense() {
		return defense;
	}

	public static int getNbCombattants() {
		return nbCombattants;
	}


	public int subirDegats(int d) {
		if (d < 0) {
			throw new IllegalArgumentException("les dégâts doivent être positifs, reçu : " + d);
		}
		int degatsReels = d - defense;
		if (degatsReels < 1) {
			degatsReels = 1;
		}
		pv -= degatsReels;
		if (pv < 0) {
			pv = 0;
		}
		return degatsReels;
	}


	public int soigner(int s) {
		if (s < 0) {
			throw new IllegalArgumentException("le soin doit être positif, reçu : " + s);
		}
		if (estKO()) {
			return 0;
		}
		int avant = pv;
		pv += s;
		if (pv > pvMax) {
			pv = pvMax;
		}
		return pv - avant;
	}

	public boolean estKO() {
		return pv == 0;
	}
	
	protected void subirDegatsBruts(int d) {
	    if (d < 0) {
	        System.out.println("les dégâts doivent être positifs, reçu : " + d);
	    }
	    pv -= d;
	    if (pv < 0) {
	        pv = 0;
	    }
	}

	public abstract String getClasse();

	@Override
	public String toString() {
	    return getClasse() + " " + nom + " [" + pv + "/" + pvMax + " PV] ATK " + attaque + " DEF " + defense;
	}


	//public static void main(String[] args) {
		//Combattant k = new Combattant("Kaelen", 18, 6, 120);
		//System.out.println(k);

		//System.out.println("\n--- Dégâts ---");
		//System.out.println("Subit 54 -> " + k.subirDegats(54) + " dégâts réels : " + k);
		//System.out.println("Subit 3 (< défense) -> " + k.subirDegats(3) + " dégât réel : " + k);

		//System.out.println("\n--- Soin ---");
		//System.out.println("Soigne 10 -> +" + k.soigner(10) + " : " + k);
		//System.out.println("Soigne 500 -> +" + k.soigner(500) + " (plafonné) : " + k);

		//System.out.println("\n--- K.O. ---");
		//k.subirDegats(999);
		//System.out.println("Après 999 dégâts : " + k + ", KO = " + k.estKO());
		//System.out.println("Soigne 50 -> +" + k.soigner(50) + " : " + k);

		//System.out.println("\nNombre de combattants : " + getNbCombattants());

		//System.out.println("\n--- Valeur invalide ---");
		//try {
			//new Combattant("Rex", 70, 10, 100);
		//} catch (IllegalArgumentException e) {
			//System.out.println("Erreur : " + e.getMessage());
		//}
	//}
}