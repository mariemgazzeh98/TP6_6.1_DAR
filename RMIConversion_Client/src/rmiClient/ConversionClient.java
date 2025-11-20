package rmiClient;

import java.rmi.Naming;
import rmiService.IConversion; // Importation possible grâce au .jar ajouté au classpath

public class ConversionClient {

    public static void main(String[] args) {
        try {
            // ii. Chercher la référence vers l'objet distant dans l'annuaire
            // L'URL doit correspondre exactement à celle définie dans le serveur :
            // "rmi://adresse_ip:port/NomObjet"
            System.out.println("Client : Recherche de l'objet distant...");
            
            // Le résultat du lookup est un objet de type Remote, on doit le caster vers l'interface IConversion
            IConversion stub = (IConversion) Naming.lookup("rmi://localhost:1099/ObjetConversion");

            // iii. Invoquer la méthode convertirMontant(500)
            double montant = 500.0;
            System.out.println("Client : Invocation de la méthode convertirMontant(" + montant + ")");
            
            double resultat = stub.convertirMontant(montant);

            // Affichage du résultat
            System.out.println("------------------------------------------");
            System.out.println("Montant initial : " + montant);
            System.out.println("Résultat de la conversion : " + resultat);
            System.out.println("------------------------------------------");

        } catch (Exception e) {
            // Gestion des erreurs (ex: Serveur éteint, objet introuvable, problème réseau)
            System.err.println("Erreur Client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}


