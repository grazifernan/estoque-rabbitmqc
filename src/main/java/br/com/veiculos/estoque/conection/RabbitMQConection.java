package br.com.veiculos.estoque.conection;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;


//notação dizendo ao sprint que a classe é autogerenciavel pelo proprio spring
@Component
public class RabbitMQConection {

    private static final String NOME_EXCHANGE = "estoque.atualizar";
    private static final String NOME_QUEUE = "estoque.atualizar";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(){
        return new Queue(NOME_QUEUE, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);

    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);

    }

    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.fila();
        DirectExchange troca = this.trocaDireta();
        Binding ligacao = this.relacionamento(filaEstoque, troca);
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareBinding(ligacao);
    }


}
