package demo.servidor;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JuntaResultadosFutureWSFutureBanco implements Runnable {
    private Future<String> futureWS;
    private Future<String> futureBanco;
    private PrintStream saidaCliente;

    public JuntaResultadosFutureWSFutureBanco(Future<String> futureWS, Future<String> futureBanco, PrintStream saidaCliente) {

        this.futureWS = futureWS;
        this.futureBanco = futureBanco;
        this.saidaCliente = saidaCliente;
    }

    @Override
    public void run() {
        System.out.println("Aguardando resultado do future WS e future Banco");
        try {
            String stringFutureWS = futureWS.get(15, TimeUnit.SECONDS);
            String stringFutureBanco = futureBanco.get();
            saidaCliente.printf("Resultado no comando c2: %s - %s \n", stringFutureWS, stringFutureBanco);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Timeout : cancelando execucao do comando c2");
            saidaCliente.println("Timeout na execucao do comando c2");
            futureWS.cancel(true);
            futureBanco.cancel(true);
            e.printStackTrace();
        }
        System.out.println("Finalizou JuntaResultadosFutureWSFutureBanco");
    }
}
