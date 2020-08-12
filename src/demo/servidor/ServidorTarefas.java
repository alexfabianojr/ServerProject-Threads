package demo.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTarefas {
    public static void main(String[] args) throws IOException {
        System.out.println("----Iniciando o servidor----");
        ServerSocket servidor = new ServerSocket(12345);
        while (true) {
            Socket socketAccept = servidor.accept();
            System.out.println("Aceitando o novo cliente " + socketAccept.getPort());
        }
    }
}
