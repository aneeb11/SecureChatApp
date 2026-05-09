package controller;
import java.io.*;
import java.net.*;
import java.util.*;


public class Server {
    public static final int PORT = 5000;
    public static List <ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Server Starting on Port : " + PORT);
        try {
            ServerSocket server_socket = new ServerSocket(PORT);
            System.out.println("✅ Server started! Waiting for clients...");
            while(true) {
                Socket client_socket = server_socket.accept();
                System.out.println("✅ New Client connected!");
                ClientHandler handler = new ClientHandler(client_socket);
                clients.add(handler);
                Thread thread = new Thread(handler);
                thread.start();
            }
        }catch (IOException e) {
            System.out.println("Error : " +e.getMessage());
        }

    }
}
