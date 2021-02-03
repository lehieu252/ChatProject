package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    public List<Message> listMsg;

    public ServerImpl() throws RemoteException {
        listMsg = new ArrayList<>();
    }

    @Override
    public void addMessage(Message message) throws RemoteException {
        this.listMsg.add(message);
        System.out.println(message.getMessString());
    }

    @Override
    public List<Message> getListMess() throws RemoteException {
        return this.listMsg;
    }


}
