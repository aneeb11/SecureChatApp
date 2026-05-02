package controller;
import java.io.*;
import java.net.*;
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

        }
        catch (IOException e)
        {
            System.out.println("❌ Could not connect: " + e.getMessage());
            System.out.println("Make Sure the server is running first!");
        }


    }
}
