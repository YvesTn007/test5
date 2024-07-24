import java.sql.*;
import java.util.HashMap;

public class Bibliotheque {
    private HashMap<Integer, Livre> livres = new HashMap<>();
    private int idCourant = 1;
    private final String URL = "jdbc:mysql://localhost:3306/bibliotheque";
    private final String USER = "root";
    private final String PASSWORD = "password";

    public Bibliotheque() {
        chargerLivres();
    }

    public void ajouterLivre(Livre livre) {
        String query = "INSERT INTO livres (id, nom, auteur, categorie) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, livre.getId());
            statement.setString(2, livre.getNom());
            statement.setString(3, livre.getAuteur());
            statement.setString(4, livre.getClass().getSimpleName());
            statement.executeUpdate();
            livres.put(livre.getId(), livre);
            System.out.println("Livre ajouté: " + livre);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du livre: " + e.getMessage());
        }
    }

    public void supprimerLivre(int id) {
        String query = "DELETE FROM livres WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                livres.remove(id);
                System.out.println("Livre avec ID " + id + " supprimé.");
            } else {
                System.out.println("Livre avec ID " + id + " non trouvé.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du livre: " + e.getMessage());
        }
    }

    public void modifierLivre(int id, String nom, String auteur) {
        String query = "UPDATE livres SET nom = ?, auteur = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            statement.setString(2, auteur);
            statement.setInt(3, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                Livre livre = livres.get(id);
                livre.setNom(nom);
                livre.setAuteur(auteur);
                System.out.println("Livre modifié: " + livre);
            } else {
                System.out.println("Livre avec ID " + id + " non trouvé.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du livre: " + e.getMessage());
        }
    }

    public void rechercherLivreParNom(String nom) {
        String query = "SELECT * FROM livres WHERE nom = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String auteur = resultSet.getString("auteur");
                    String categorie = resultSet.getString("categorie");
                    afficherLivre(id, nom, auteur, categorie);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du livre: " + e.getMessage());
        }
    }

    public void listerLivresParLettre(char lettre) {
        String query = "SELECT * FROM livres WHERE nom LIKE ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lettre + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String auteur = resultSet.getString("auteur");
                    String categorie = resultSet.getString("categorie");
                    afficherLivre(id, nom, auteur, categorie);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la liste des livres: " + e.getMessage());
        }
    }

    public void afficherNombreDeLivres() {
        System.out.println("Nombre total de livres: " + livres.size());
    }

    public void afficherLivresParCategorie(String categorie) {
        String query = "SELECT * FROM livres WHERE categorie = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, categorie);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String auteur = resultSet.getString("auteur");
                    afficherLivre(id, nom, auteur, categorie);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des livres par catégorie: " + e.getMessage());
        }
    }

    public void afficherDetailsLivre(int id) {
        String query = "SELECT * FROM livres WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String auteur = resultSet.getString("auteur");
                    String categorie = resultSet.getString("categorie");
                    afficherLivre(id, nom, auteur, categorie);
                } else {
                    System.out.println("Livre avec ID " + id + " non trouvé.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des détails du livre: " + e.getMessage());
        }
    }

    public void listerTousLesLivres() {
        String query = "SELECT * FROM livres";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String auteur = resultSet.getString("auteur");
                String categorie = resultSet.getString("categorie");
                afficherLivre(id, nom, auteur, categorie);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la liste de tous les livres: " + e.getMessage());
        }
    }

    public void rechercherLivresParAuteur(String auteur) {
        String query = "SELECT * FROM livres WHERE auteur = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, auteur);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String categorie = resultSet.getString("categorie");
                    afficherLivre(id, nom, auteur, categorie);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des livres par auteur: " + e.getMessage());
        }
    }

    public int genererId() {
        return idCourant++;
    }

    private void chargerLivres() {
        String query = "SELECT * FROM livres";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String auteur = resultSet.getString("auteur");
                String categorie = resultSet.getString("categorie");
                Livre livre;
                switch (categorie.toLowerCase()) {
                    case "roman":
                        livre = new Roman(id, nom, auteur);
                        break;
                    case "sciencefiction":
                        livre = new ScienceFiction(id, nom, auteur);
                        break;
                    case "biographie":
                        livre = new Biographie(id, nom, auteur);
                        break;
                    default:
                        throw new IllegalArgumentException("Catégorie de livre inconnue: " + categorie);
                }
                livres.put(id, livre);
                if (id >= idCourant) {
                    idCourant = id + 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du chargement des livres: " + e.getMessage());
        }
    }

    private void afficherLivre(int id, String nom, String auteur, String categorie) {
        System.out.println("ID: " + id + ", Nom: " + nom + ", Auteur: " + auteur + ", Catégorie: " + categorie);
    }
}