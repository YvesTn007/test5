public class Biographie extends Livre {
    public Biographie(int id, String nom, String auteur) {
        super(id, nom, auteur);
    }

    @Override
    public String toString() {
        return "Biographie - " + super.toString();
    }
}
