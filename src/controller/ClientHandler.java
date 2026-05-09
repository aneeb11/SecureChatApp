package controller;
import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable{
    private Socket client_socket;
    private String client_name;
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
            writer.println("Connected to Server! Enter your name : ");
            client_name=reader.readLine();
            System.out.println("✅ " + client_name + " joined the chat!");
            String message;
            while ((message= reader.readLine())!=null)
            {
                System.out.println(client_name+ ": "+ message);
                if (message.equalsIgnoreCase("exit"))
                {
                    System.out.println(client_name+" left the chat. ");
                    break;
                }
            }

        }
        catch (IOException e)
        {
            System.out.println("❌ Error : "+e.getMessage());
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
