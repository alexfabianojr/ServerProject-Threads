package demo.servidor;

import java.net.Socket;

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
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
