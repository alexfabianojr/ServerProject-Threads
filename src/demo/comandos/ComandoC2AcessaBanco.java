package demo.comandos;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2AcessaBanco implements Callable<String> {

    PrintStream saida;

    public ComandoC2AcessaBanco(PrintStream saida) {
        this.saida = saida;
    }

    @Override
    public String call() throws Exception {

        saida.println("Confirmação comando c2 Banco");
        System.out.println("Processando c2 Banco");
        int numero = new Random().nextInt(1000) +1;
        Thread.sleep(14500);
        saida.println("Comando c2 Banco executado com sucesso");

        return String.valueOf(numero);
    }
}
