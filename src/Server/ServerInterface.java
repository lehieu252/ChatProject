package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {
    void addMessage(Message message) throws RemoteException;
    List<Message> getListMess() throws RemoteException;

}
