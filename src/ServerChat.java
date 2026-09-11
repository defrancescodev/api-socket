import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ServerChat {
    private static final int port = 12345;
    public static Set<PrintWriter> connectedClients = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        try  (ServerSocket serverSocket = new ServerSocket(port)){
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread (new Handler(socket)).start();;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
