import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket socket;
    private PrintWriter exit;
    private BufferedReader input;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            exit = new PrintWriter(socket.getOutputStream(), true);
            ServerChat.connectedClients.add(exit);

            String mensagem;
            while ((mensagem = input.readLine()) != null) {
                System.out.println("Mensagem: " + mensagem);
                transmitirParaTodos(mensagem);
            }
        } catch (Exception e) {
            System.out.println("Cliente desconectado.");
            e.printStackTrace();
        } finally {
            if (exit != null) {
                ServerChat.connectedClients.remove(exit);
            }
            try { socket.close(); } catch (Exception e) {}
        }
    }

    private void transmitirParaTodos(String mensagem) {
        synchronized (ServerChat.connectedClients) {
            for (PrintWriter cliente : ServerChat.connectedClients) {
                cliente.println(mensagem);
            }
        }
    }
}
