package rmiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject; // <--- 1. Import nécessaire

// 2. AJOUTEZ 'extends UnicastRemoteObject'
public class ConversionImpl extends UnicastRemoteObject implements IConversion {

    // 3. Le constructeur doit être présent et lancer RemoteException
    public ConversionImpl() throws RemoteException {
        super(); // Appelle le constructeur de UnicastRemoteObject qui exporte l'objet
    }

    @Override
    public double convertirMontant(double mt) throws RemoteException {
        System.out.println("Conversion du montant : " + mt);
        return mt * 3.3;
    }
}





