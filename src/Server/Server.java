package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server is starting...");
        LocateRegistry.createRegistry(1099);
        ServerImpl serverRMI = new ServerImpl();
//        Thread rmiThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
                try {
                    String url1 = "rmi://localhost/Message";
                    Naming.rebind(url1, serverRMI);
                    System.out.println("RMI is ready...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
//            }
//        });

//        Thread socketThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
                ServerSocket listener = null;
                int clientNumber = 0;

                try {
                    listener = new ServerSocket(80);
                } catch (IOException e) {
                    System.out.println(e);
                    System.exit(1);
                }

                try {
                    while (true) {
                        Socket socketOfServer = listener.accept();
                        new ServiceThread(clientNumber++, socketOfServer,serverRMI.getListMess()).start();
                    }
                } catch (IOException e) {
                    System.out.println(e);
                } finally {
                    try {
                        listener.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//            }
//        });



//        rmiThread.start();
//        socketThread.start();

    }
}
