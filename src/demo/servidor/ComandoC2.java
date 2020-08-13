package demo.servidor;

import java.io.PrintStream;
import java.util.Arrays;

public class ComandoC2 implements Runnable {

    PrintStream saida;

    public ComandoC2(PrintStream saida) {
        this.saida = saida;
    }

    @Override
    public void run() {
        try {
            saida.println("Confirmação comando c2");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
        saida.println("Comando executado com sucesso");
    }
}
