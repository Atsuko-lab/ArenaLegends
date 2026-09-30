package StatsArene;

public class Guerrier extends Combattant {
    private int rage; 

    public Guerrier(String nom, int attaque, int defense, int pvMax) {
        super(nom, attaque, defense, pvMax);
        this.rage = 0;
    }

    public int getRage() { return rage; }

    @Override
    public int attaquer(Combattant cible) {
        int degats = getAttaque();
        rage += 20;
        if (rage >= 100) {
            degats *= 2;
            rage = 0;
        }
        cible.subirDegats(degats);
        return degats;
    }
    
    @Override
    public String getClasse() { return "Guerrier"; }
}
