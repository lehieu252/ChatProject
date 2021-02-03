package Server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Message implements Serializable {
    private String name;
    private String text;

    public Message(String name, String text) throws RemoteException {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessString()  throws RemoteException {
        return "[ " + this.name + " ]: " + this.text;
    }


}