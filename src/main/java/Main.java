import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        // You can use print statements as follows for debugging, they'll be visible when running tests.
        System.out.println("Logs from your program will appear here!");

        //  Uncomment this block to pass the first stage
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        int port = 6379;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            // Wait for connection from client.
            clientSocket = serverSocket.accept();
            new ServerThread(clientSocket).start();
//            InputStream input = clientSocket.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//            OutputStream output = clientSocket.getOutputStream();
//            String line;
//            while (true) {
//                try {
//                    if ((line = reader.readLine()) != null) {
//                        //Show them
//                        if (line.startsWith("*") || line.startsWith("$"))
//                            continue;
//                        PrintWriter writer = new PrintWriter(output, true);
//                        writer.println("+PONG\r");
//                        System.out.println(line);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }
}
