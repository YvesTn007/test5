import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = new Bibliotheque();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\nSystème de Gestion de Bibliothèque");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Supprimer un livre");
            System.out.println("3. Modifier un livre");
            System.out.println("4. Rechercher un livre par nom");
            System.out.println("5. Lister les livres par lettre");
            System.out.println("6. Afficher le nombre de livres");
            System.out.println("7. Afficher les livres par catégorie");
            System.out.println("8. Afficher les détails d'un livre par ID");
            System.out.println("9. Lister tous les livres");
            System.out.println("10. Rechercher des livres par auteur");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine(); // consommer la nouvelle ligne

            try {
                switch (choix) {
                    case 1:
                        System.out.print("Entrez le type de livre (Roman, ScienceFiction, Biographie): ");
                        String type = scanner.nextLine();
                        System.out.print("Entrez le nom du livre: ");
                        String nom = scanner.nextLine();
                        System.out.print("Entrez l'auteur du livre: ");
                        String auteur = scanner.nextLine();
                        Livre livre = null;
                        int id = bibliotheque.genererId();
                        switch (type.toLowerCase()) {
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
                                System.out.println("Type de livre invalide.");
                                continue;
                        }
                        bibliotheque.ajouterLivre(livre);
                        break;
                    case 2:
                        System.out.print("Entrez l'ID du livre à supprimer: ");
                        int idSupprimer = scanner.nextInt();
                        bibliotheque.supprimerLivre(idSupprimer);
                        break;
                    case 3:
                        System.out.print("Entrez l'ID du livre à modifier: ");
                        int idModifier = scanner.nextInt();
                        scanner.nextLine(); // consommer la nouvelle ligne
                        System.out.print("Entrez le nouveau nom du livre: ");
                        String nouveauNom = scanner.nextLine();
                        System.out.print("Entrez le nouvel auteur du livre: ");
                        String nouvelAuteur = scanner.nextLine();
                        bibliotheque.modifierLivre(idModifier, nouveauNom, nouvelAuteur);
                        break;
                    case 4:
                        System.out.print("Entrez le nom du livre à rechercher: ");
                        String nomRechercher = scanner.nextLine();
                        bibliotheque.rechercherLivreParNom(nomRechercher);
                        break;
                    case 5:
                        System.out.print("Entrez la lettre initiale pour lister les livres: ");
                        char lettre = scanner.nextLine().charAt(0);
                        bibliotheque.listerLivresParLettre(lettre);
                        break;
                    case 6:
                        bibliotheque.afficherNombreDeLivres();
                        break;
                    case 7:
                        System.out.print("Entrez la catégorie pour lister les livres: ");
                        String categorieRechercher = scanner.nextLine();
                        bibliotheque.afficherLivresParCategorie(categorieRechercher);
                        break;
                    case 8:
                        System.out.print("Entrez l'ID du livre pour afficher les détails: ");
                        int idDetails = scanner.nextInt();
                        bibliotheque.afficherDetailsLivre(idDetails);
                        break;
                    case 9:
                        bibliotheque.listerTousLesLivres();
                        break;
                    case 10:
                        System.out.print("Entrez l'auteur pour rechercher des livres: ");
                        String auteurRechercher = scanner.nextLine();
                        bibliotheque.rechercherLivresParAuteur(auteurRechercher);
                        break;
                    case 0:
                        System.out.println("Quitter...");
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        } while (choix != 0);

        scanner.close();
    }
}