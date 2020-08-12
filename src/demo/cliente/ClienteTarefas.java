package demo.cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Conexao estabelecida");
        PrintStream saida = new PrintStream(socket.getOutputStream());
        saida.println("comando 1");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
        saida.close();
        socket.close();
    }
}
