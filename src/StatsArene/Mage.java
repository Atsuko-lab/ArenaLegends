package StatsArene;

public class Mage extends Combattant {
    private int mana;

    public Mage(String nom, int attaque, int defense, int pvMax) {
        super(nom, attaque, defense, pvMax);
        this.mana = 100;
    }

    public int getMana() { return mana; }

    @Override
    public int attaquer(Combattant cible) {
        int degats;
        if (mana >= 30) {
            mana -= 30;
            degats = getAttaque() * 2;
            cible.subirDegatsBruts(degats);
        } else {
            degats = getAttaque() / 2;
            mana = Math.min(100, mana + 15);
            cible.subirDegats(degats);
        }
        return degats;
    }
    
    @Override
    public String getClasse() { return "Mage"; }
}
