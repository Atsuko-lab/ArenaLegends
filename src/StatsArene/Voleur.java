package StatsArene;
import java.util.Random;

public class Voleur extends Combattant {
    private static final Random random = new Random();
    private int esquive; 

    public Voleur(String nom, int attaque, int defense, int pvMax, int esquive) {
        super(nom, attaque, defense, pvMax);
        if (esquive < 10 || esquive > 40) {
            System.out.println("esquive doit être entre 10 et 40, reçu : " + esquive);
        }
        this.esquive = esquive;
    }

    @Override
    public int attaquer(Combattant cible) {
        int total = getAttaque();
        cible.subirDegats(getAttaque());
        if (random.nextInt(100) < 25) {          
            cible.subirDegats(getAttaque());
            total += getAttaque();
        }
        return total;
    }
    
    @Override
    public int subirDegats(int d) {
        if (random.nextInt(100) < esquive) {
            return 0;                   
        }
        return super.subirDegats(d);     
    }
    
    @Override
    public String getClasse() { return "Voleur"; }
}