public class ScienceFiction extends Livre {
    public ScienceFiction(int id, String nom, String auteur) {
        super(id, nom, auteur);
    }

    @Override
    public String toString() {
        return "Science-Fiction - " + super.toString();
    }
}
