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

            Thread threadEnviaComando = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (sc.hasNextLine()) {
                        String linha = sc.nextLine();
                        if (linha.trim().equals("")) {
                            break;
                        }
                        saida.println(linha);
                    }
                }
            });

            Thread threadRecebeRespostaDoServidor = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Recebendo dados do servidor");
                        Scanner respostaServidor = new Scanner(socket.getInputStream());
                        while (respostaServidor.hasNextLine()) {
                            String linha = respostaServidor.nextLine();
                            System.out.println(linha);
                        }
                    } catch (IOException e) {
                        System.out.println(Arrays.toString(e.getStackTrace()));
                        throw new RuntimeException();
                    }
                }
            });

            threadEnviaComando.start();
            threadRecebeRespostaDoServidor.start();

            threadEnviaComando.join();

            System.out.println("Fechando socket do cliente");

        } catch (IOException | InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException();
        }
    }
}
