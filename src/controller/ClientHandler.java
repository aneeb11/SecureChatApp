package controller;
import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable{
    private Socket client_socket;
    private String client_name;
    public ClientHandler(Socket client_socket)
    {
        this.client_socket=client_socket;
    }
    @Override
    public void run() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            PrintWriter writer = new PrintWriter(client_socket.getOutputStream(),true);
            writer.println("Connected to Server! Enter your name : ");
            client_name=reader.readLine();
            System.out.println("✅ " + client_name + " joined the chat!");
            String message;
            while ((message= reader.readLine())!=null)
            {
                System.out.println(client_name+ ": "+ message);
                if (message.equalsIgnoreCase("exit"))
                {
                    System.out.println(client_name+" lef the chat. ");
                    break;
                }
            }

        }
        catch (IOException e)
        {
            System.out.println("❌ Error : "+e.getMessage());
        }
    }
}
