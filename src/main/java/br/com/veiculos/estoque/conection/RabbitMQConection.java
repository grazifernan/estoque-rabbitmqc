package br.com.veiculos.estoque.conection;

import br.com.veiculos.estoque.constantes.RabbitmqConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;


//notação dizendo ao sprint que a classe é autogerenciavel pelo proprio spring
@Component
public class RabbitMQConection {

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(){
        return new Queue(RabbitmqConstantes.NOME_QUEUE, true, false, false);
    }

    private DirectExchange directExchange(){
        return new DirectExchange(RabbitmqConstantes.NOME_EXCHANGE);

    }

    private Binding binding(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);

    }

    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.queue();
        DirectExchange troca = this.directExchange();
        Binding ligacao = this.binding(filaEstoque, troca);
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareBinding(ligacao);
    }


}
