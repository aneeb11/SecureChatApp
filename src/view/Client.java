package view;
import controller.ClientHandler;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP ="localhost";
    private static final int PORT = 5000;
    public static void main(String[] args) {
        System.out.println("Connecting to Server......");
        try
        {
            Socket socket = new Socket(SERVER_IP,PORT);
            System.out.println("✅ Connected to server!");
            BufferedReader ServerReader = new BufferedReader
                    (new InputStreamReader(socket.getInputStream()));
            PrintWriter ServerWriter =
                    new PrintWriter(socket.getOutputStream(),true);
            Scanner keyboard = new Scanner(System.in);
            Thread listenThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = ServerReader.readLine()) != null) {
                        System.out.println("\r"+response);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            System.out.print("Enter your name: ");
            String name = keyboard.nextLine();
            ServerWriter.println(name);
            listenThread.start();
            System.out.println("You : ");
            while (keyboard.hasNextLine())
            {
                String message = keyboard.nextLine();
                ServerWriter.println(message);
                if(message.equalsIgnoreCase("exit"))
                {
                    break;
                }

            }

        }
        catch (IOException e)
        {
            System.out.println("❌ Could not connect: " + e.getMessage());
            System.out.println("Make Sure the server is running first!");
        }


    }
}
