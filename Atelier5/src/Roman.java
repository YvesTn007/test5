public class Roman extends Livre {
    public Roman(int id, String nom, String auteur) {
        super(id, nom, auteur);
    }

    @Override
    public String toString() {
        return "Roman - " + super.toString();
    }
}
