package demo.servidor;

import java.io.IOException;
import java.io.PrintStream;
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
        try (Scanner sc = new Scanner(socket.getInputStream());
             PrintStream saidaCliente = new PrintStream(socket.getOutputStream())) {
            while (sc.hasNextLine()) {
                String comando = sc.nextLine();
                System.out.printf("Comando recebido %s", comando);
                switch (comando) {
                    case "c1" -> {
                        saidaCliente.println("Confirmação comando c1");
                    }
                    case "c2" -> {
                        saidaCliente.println("Confirmação comando c2");
                    }
                    default -> {
                        saidaCliente.println("Comando nao encontrado");
                    }
                }


                System.out.println(comando);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
