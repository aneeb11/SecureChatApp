package controller;
import java.io.*;
import java.net.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP ="localhost";
    private static final int PORT = 5000;
    public static <exceptionID> void main(String[] args) {
        System.out.println("Connecting to Server......");
        try
        {
            Socket Socket = new Socket(SERVER_IP,PORT);
            System.out.println("✅ Connected to server!");
            BufferedReader ServerReader = new BufferedReader
                    (new InputStreamReader(Socket.getInputStream()));
            PrintWriter ServerWriter =
                    new PrintWriter(Socket.getOutputStream());
            Scanner keyboard = new Scanner(System.in);
            Thread listenThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = ServerReader.readLine()) != null) {
                        System.out.println("Server: " + response);
                        System.out.print("You: ");
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            listenThread.start();
            System.out.println("You : ");
            while (keyboard.hasNextLine())
            {
                String message = keyboard.nextLine();
                ServerWriter.println(message);
                if(message.equalsIgnoreCase("exit"))break;
                System.out.println("You : ");
            }

        }
        catch (IOException e)
        {
            System.out.println("❌ Could not connect: " + e.getMessage());
            System.out.println("Make Sure the server is running first!");
        }


    }
}
