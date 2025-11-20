package rmiService;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConversion extends Remote {
    // La méthode doit lancer une RemoteException
    public double convertirMontant(double mt) throws RemoteException;
}

