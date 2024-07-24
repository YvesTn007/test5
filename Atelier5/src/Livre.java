public class Livre {
    private int id;
    private String nom;
    private String auteur;

    public Livre(int id, String nom, String auteur) {
        this.id = id;
        this.nom = nom;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + nom + ", Auteur: " + auteur;
    }
}
