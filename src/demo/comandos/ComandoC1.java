package demo.comandos;

import java.io.PrintStream;
import java.util.Arrays;

public class ComandoC1 implements Runnable {

    PrintStream saida;

    public ComandoC1(PrintStream saida) {
        this.saida = saida;
    }

    @Override
    public void run() {

        try {
            saida.println("Confirmação comando c1");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }

        saida.println("Comando executado com sucesso");
    }
}
