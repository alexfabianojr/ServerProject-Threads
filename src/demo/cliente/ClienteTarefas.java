package demo.cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ClienteTarefas {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintStream saida = new PrintStream(socket.getOutputStream());
             Scanner sc = new Scanner(System.in);) {
            System.out.println("Conexao estabelecida");
            saida.println("comando 1");
            sc.nextLine();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
