import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        final InetAddress HOST = InetAddress.getLocalHost();
        // getting address information about localhost
        final int PORT = 6765;
        // initializing the same port number
        Socket clientSocket = new Socket(HOST, PORT);
        // creating client socket and connecting it to the server

        Scanner input = new Scanner(System.in);
        // creating input stream

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // creating INPUT stream attached to the socket

        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        // creating OUTPUT stream attached to the socket

        String request = input.nextLine();
        System.out.println(request);
        // reading line from user input
        outToServer.println(request);
        // sending line from user input

        String response = inFromServer.readLine();
        System.out.println(response);
        // reading line from server

        if (response.equals("Username?")) {
            out.println(input.nextLine()); //username
            System.out.println(in.readLine());
            out.println(input.nextLine()); //password
            System.out.println(in.readLine());
        }

        clientSocket.close();
        // closing the connection
    }
}
