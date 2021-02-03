package Server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServiceThread extends Thread {
    private int clientNumber;
    private Socket socketOfServer;
    private List<Message> messageList;

    public ServiceThread(int clientNumber, Socket socketOfServer, List<Message> messageList) {
        this.clientNumber = clientNumber;
        this.socketOfServer = socketOfServer;
        this.messageList = messageList;
        System.out.println("New connection with client # " + this.clientNumber + " at " + socketOfServer);
    }

    @Override
    public void run() {
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

            Message msg = new Message("Server", "Welcome to chat room !");
            os.write(msg.getMessString());
            os.newLine();
            os.flush();

            int prevSize = 0;

            while (true) {
                Thread.sleep(100);
                if(messageList.size() > prevSize ){
                    os.write(messageList.get(messageList.size() - 1).getMessString());
                    os.newLine();
                    os.flush();
                    prevSize = messageList.size();
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
