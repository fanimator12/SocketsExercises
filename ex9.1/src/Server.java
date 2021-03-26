import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        final int PORT = 6765;
        // initializing port number
        ServerSocket welcomeSocket = new ServerSocket(PORT);
        // creating socket at that port for incoming request

        System.out.println("Starting server... ");

        while (true) {
            System.out.println("Waiting for a client... ");

            Socket connectionSocket = welcomeSocket.accept();
            // waiting for contact by client on welcoming socket

            new Thread(() -> {
                try {
                    System.out.printf("%s connected\n", connectionSocket.getInetAddress().toString());
//                    Socket socket = serve;
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

                    PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);

                    String message = inFromClient.readLine();
                    // reading line from client
                    System.out.println(message);

                    outToClient.println("Message: " + message);
                    // sending line to client

                    if (!request.equals("connect"))
                        outToClient.println("Disconnected");
                    else {
                        outToClient.println("Username?");
                        inFromClient.readLine(); //username
                        outToClient.println("Password?");
                        inFromClient.readLine(); //password
                        outToClient.println("Approved");
                    }

                    connectionSocket.close();
                    // closing the connection

                } catch (IOException e) {
                    //at least we tried
                }
            }).start();
        }
    }
}
