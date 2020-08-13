package demo.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2ChamaWS implements Callable<String> {

    PrintStream saida;

    public ComandoC2ChamaWS(PrintStream saida) {
        this.saida = saida;
    }

    @Override
    public String call() throws Exception {

        saida.println("Confirmação comando c2 WS");
        System.out.println("Processando c2 WS");
        int numero = new Random().nextInt(1000) +1;
        Thread.sleep(14000);
        saida.println("Comando c2 WS executado com sucesso");

        return String.valueOf(numero);
    }
}
