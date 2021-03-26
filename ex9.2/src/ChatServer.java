import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private static synchronized void sendMessages(String message, ArrayList<PrintWriter> clients) {
        new Thread(() -> {
            for (PrintWriter client : clients) {
                client.println(message);
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        final int PORT = 6969;
        ArrayList<String> messages = new ArrayList<String>();
        ArrayList<PrintWriter> clients = new ArrayList<PrintWriter>();

        System.out.println("Starting server... ");
        ServerSocket welcomeSocket = new ServerSocket(PORT);

        while (true) {
//            System.out.println("Waiting for a client... ");
            Socket socket = welcomeSocket.accept();
            new Thread(() -> {
                System.out.printf("%s connected\n", socket.getInetAddress().toString());
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    clients.add(out);
                    String request = in.readLine();

                    if (!request.equals("connect"))
                        out.println("Disconnected");
                    else {
                        out.println("Connected");
                        String msg = null;
                        String reply = null;
                        do {
                            msg = in.readLine();
                            if (msg.equals("exit"))
                                reply = String.format("%s disconnected\n", socket.getInetAddress());
                            else
                                reply = String.format(">%s says: %s\n", socket.getInetAddress().toString(), msg);
                            System.out.print(reply);
                            messages.add(reply);
//                            out.println(reply);
                            sendMessages(reply, clients);
                        } while (!msg.equals("exit"));
                    }
                    socket.close();
                } catch (IOException e) {
                    //at least you tried
                }
            }).start();
        }
    }
}
