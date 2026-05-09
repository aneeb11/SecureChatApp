package controller;
import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable{
    private Socket client_socket;
    public  String client_name;
    private PrintWriter writer;
    public ClientHandler(Socket client_socket)
    {
        this.client_socket=client_socket;
    }
    @Override
    public void run() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            writer = new PrintWriter(client_socket.getOutputStream(),true);
            client_name=reader.readLine();
            System.out.println("DEBUG: client_name = " + client_name);
            System.out.println("✅ " + client_name + " joined the chat!");;
            broadcast("✅ " + client_name + " joined the chat!");
            String message;
            while ((message= reader.readLine())!=null) {
                System.out.println(client_name + ": " + message);
                broadcast(client_name + " : " + message);
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println(client_name + " left the chat. ");
                    break;
                }
            }


        }
        catch (IOException e)
        {
            System.out.println("❌ Error : "+e.getMessage());
        }
        finally {
            Server.clients.remove(this);
        }
        try{
            client_socket.close();
            System.out.println(client_name+" removed from chat.");
        }
        catch (IOException e)
        {
            System.out.println("Error while closing socket : "+e.getMessage());
        }
    }
    public void broadcast(String message)

    {
        for(ClientHandler client : Server.clients)
        {
            if(client!=this)
            {
                client.writer.println(message);
            }
        }
    }
}
