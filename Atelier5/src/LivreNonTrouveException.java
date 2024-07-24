public class LivreNonTrouveException extends Exception {
    public LivreNonTrouveException(int id) {
        super("Livre avec ID " + id + " non trouvé.");
    }
}