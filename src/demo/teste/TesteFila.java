package demo.teste;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* Ao tentar adicionar um elemento na fila "sem vaga", o método put() bloqueia a execução
aguardando por uma vaga dentro da lista. Já o método offer() não bloqueia e retorna false
caso não haja mais espaço na fila. Analogamente há também os métodos poll() e take() para pegar um elemento da fila.
O método take() irá esperar a chegada de um novo elemento. Já o método poll() permite que seja estabelecido um Timeout
para a espera de um novo elemento.*/

public class TesteFila {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> fila = new ArrayBlockingQueue<>(3);
        fila.put("c1");
        fila.put("c2");
        fila.put("c3");

        fila.offer("Nao bloqueia");
        fila.put("Bloqueia");

        System.out.println(fila.size());

        System.out.println(fila.take());
        System.out.println(fila.take());
        System.out.println(fila.take());

        System.out.println(fila.size());
    }
}
