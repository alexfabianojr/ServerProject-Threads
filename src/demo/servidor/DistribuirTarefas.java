package demo.servidor;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefas implements Runnable {
    private Socket socket;

    public DistribuirTarefas() {
    }

    public DistribuirTarefas(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Distribuindo Tarefas para " + socket);
        try (Scanner sc = new Scanner(socket.getInputStream())) {
            while (sc.hasNextLine()) {
                String comando = sc.nextLine();
                System.out.println(comando);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
