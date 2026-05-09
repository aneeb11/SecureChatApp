package controller;
import java.io.*;
import java.net.*;


public class Server {
    public static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Server Starting on Port : " + PORT);
        try {
            ServerSocket server_socket = new ServerSocket(PORT);
            System.out.println("✅ Server started! Waiting for client...");
            Socket client_socket = server_socket.accept();
            System.out.println("✅ Client connected!");
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(client_socket.getInputStream()));
            PrintWriter writer = new PrintWriter(client_socket.getOutputStream(),true);
            String message;
            while((message = reader.readLine())!=null)
            {
                System.out.println("Client Says : " + message);
                writer.println(message);
                if(message.equalsIgnoreCase("exit"))
                {
                    System.out.println("Client Disconnected.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error : " +e.getMessage());
        }

    }
}
