import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        final String HOST = "localhost";
        final int PORT = 6969;

        Scanner input = new Scanner(System.in);

        Socket clientSocket = new Socket(HOST, PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        out.println(input.nextLine());

        String reply = in.readLine();
        System.out.println(reply);
        if (reply.equals("Connected")) {
            Thread thread = new Thread(() -> {
                try {
                    while (true)
                        System.out.println(in.readLine());
                } catch (IOException e) {
                    //at least you tried
                }
            });
            thread.setDaemon(true);
            thread.start();
            String msg = null;
            do {
                msg = input.nextLine();
                out.println(msg);
            } while (!msg.equals("exit"));
        }

        clientSocket.close();
    }
}
