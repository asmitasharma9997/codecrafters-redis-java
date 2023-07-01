import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            String line;
            while (true) {
                if ((line = reader.readLine()) != null) {
                    //Show them
                    if (line.startsWith("*") || line.startsWith("$"))
                        continue;
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println("+PONG\r");
                    System.out.println(line);
                }
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
