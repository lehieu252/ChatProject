package Client;

import Server.Message;
import Server.ServerInterface;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InterruptedException {


        Thread rmiThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.println("----------------");
                    ServerInterface serverRMI = (ServerInterface) Naming.lookup("rmi://localhost/Message");
                    Message welcomMess = new Message(name, "Client " + name + " has joined the chat !");
                    serverRMI.addMessage(welcomMess);
                    while (true) {
                        String msg = sc.nextLine();
                        Message message = new Message(name, msg);
                        serverRMI.addMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        rmiThread.start();

        Thread socketThread = new Thread(new Runnable() {
            @Override
            public void run() {
                final String serverHost = "localhost";
                Socket socketOfClient = null;
                BufferedWriter os = null;
                BufferedReader is = null;
                try {
                    socketOfClient = new Socket(serverHost, 80);
                    os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
                    is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
                    String newMessage;
                    while ((newMessage = is.readLine()) != null) {
                        System.out.println(newMessage);
                    }
                    os.close();
                    is.close();
                    socketOfClient.close();
                } catch (UnknownHostException e) {
                    System.err.println("Don't know about host " + serverHost);
                    e.printStackTrace();
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to " + serverHost);
                    e.printStackTrace();
                }

            }
        });
        socketThread.start();

    }
}
