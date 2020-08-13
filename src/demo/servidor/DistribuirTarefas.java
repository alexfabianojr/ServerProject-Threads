package demo.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
            ExecutorService threadPool = Executors.newCachedThreadPool();
            while (sc.hasNextLine()) {
                String comando = sc.nextLine();
                System.out.printf("Comando recebido %s", comando);
                switch (comando) {
                    case "c1" -> {
                        ComandoC1 c1 = new ComandoC1(saidaCliente);
                        threadPool.execute(c1);
                    }
                    case "c2" -> {
                        ComandoC2ChamaWS c2WS = new ComandoC2ChamaWS(saidaCliente);
                        ComandoC2AcessaBanco c2Banco = new ComandoC2AcessaBanco(saidaCliente);
                        Future<String> futureWS = threadPool.submit(c2WS);
                        Future<String> futureBanco = threadPool.submit(c2Banco);
                        threadPool.submit(new JuntaResultadosFutureWSFutureBanco(futureWS, futureBanco, saidaCliente));
                    }
                    case "fim" -> saidaCliente.println("Desligando o servidor");
                    default -> saidaCliente.println("Comando nao encontrado");
                }


                System.out.println(comando);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
