package StatsArene;

public class Paladin extends Combattant {
    private int foi;

    public Paladin(String nom, int attaque, int defense, int pvMax) {
        super(nom, attaque, defense, pvMax);
        this.foi = 0;
    }

    public int getFoi() { return foi; }

    @Override
    public int attaquer(Combattant cible) {
        int degats = getAttaque();
        foi++;
        if (foi >= 3) {
            soigner(getPvMax() * 10 / 100);
            foi = 0;
        }
        cible.subirDegats(degats);
        return degats;
    }

    @Override
    public String getClasse() { return "Paladin"; }
}