package demo.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {
    public static void main(String[] args) {
        System.out.println("----Iniciando o servidor----");
        try (ServerSocket servidor = new ServerSocket(12345)) {
            ExecutorService threadPool = Executors.newCachedThreadPool();
            while (true) {
                Socket socketAccept = servidor.accept();
                System.out.println("Aceitando o novo cliente " + socketAccept.getPort());
                DistribuirTarefas tarefas = new DistribuirTarefas(socketAccept);
                threadPool.execute(tarefas);
                Thread.sleep(20000);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
