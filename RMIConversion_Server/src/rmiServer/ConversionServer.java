package rmiServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import rmiService.ConversionImpl;
import rmiService.IConversion;

public class ConversionServer {

    public static void main(String[] args) {
        try {
            // i. Activer l'annuaire RMIRegistry sur le port 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("Serveur : Annuaire RMI démarré sur le port 1099.");

            // ii. Créer une instance de l'implémentation (l'objet distant)
            IConversion od = new ConversionImpl();
            System.out.println("Serveur : Instanciation de l'objet distant.");

            // iii. Publier la référence de cet objet dans l'annuaire
            // L'URL est sous la forme : rmi://adresse:port/NomDeLobjet
            Naming.rebind("rmi://localhost:1099/ObjetConversion", od);
            
            System.out.println("Serveur : Objet publié. En attente de clients...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


