package demo.servidor;

import java.util.concurrent.BlockingQueue;

public class TarefaConsumir implements Runnable {

    BlockingQueue<String> filaComandos;

    public TarefaConsumir(BlockingQueue<String> filaComandos) {
        this.filaComandos = filaComandos;
    }

    @Override
    public void run() {
        try {

            String comando = filaComandos.take();
            System.out.println("Consumindo comando c3");
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
