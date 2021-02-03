package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageInterface extends Remote {
    String getMessString() throws RemoteException;
}
