import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            PrintWriter exit = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Conectado! Digite algo:");
            String texto;
            while ((texto = keyboard.readLine()) != null) {
                exit.println(texto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
