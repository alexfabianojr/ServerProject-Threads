package demo.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {
    public static void main(String[] args) {

        System.out.println("----Iniciando o servidor----");

        try (ServerSocket servidor = new ServerSocket(12345)) {

            ExecutorService threadPool = Executors.newCachedThreadPool(new FabricaDeThreads());
            BlockingQueue<String> filaComandos = new ArrayBlockingQueue<>(4);

            while (true) {
                Socket socketAccept = servidor.accept();
                System.out.println("Aceitando o novo cliente " + socketAccept.getPort());
                DistribuirTarefas tarefas = new DistribuirTarefas(socketAccept, filaComandos);
                threadPool.execute(tarefas);
                Thread.sleep(20000); //controlando a velocidade do loop
            }

        } catch (IOException | InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
/*
* -> ExecutorService eh uma framework da JDK que permite a execucao de tarefas de maneira assincrona
* o executor service prove uma pool de threads e uma API para receber tarefas, e, de modo geral, nao fecha-se
* a pool após a execucao do servico, mas sim, mantem-se ela disponivel para futuro uso/requerimento
* -> Thread Pool eh um conjunto de threads que estão prontas (disponíveis) para uso
* -> Interface Future : permite que a variável fique na espera de um resultado futuro do tipo Object<Future>
* para isso seus metodos get() e invokeAll() são threads bloqueantes. Atraves de seus metodos eh possivel obter um resultado
* de uma execucao futura e tambem saber se o status da thread eh running ou executed. As classes que devem retornar o
* resultado futuro devem implementar Callable<?>
* as frameworks JDK fork/join podem ser substitutas do ExecutorService
* -> Socket eh um ponto de comunicacao entre duas maquinas (ou no nosso caso entre dois mains), normalmente servidor <-> cliente
*
* Links
* https://www.baeldung.com/java-executor-service-tutorial
* https://www.devmedia.com.br/java-sockets-criando-comunicacoes-em-java/9465
* https://www.baeldung.com/java-blocking-queue
*  */
