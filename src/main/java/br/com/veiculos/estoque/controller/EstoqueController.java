package br.com.veiculos.estoque.controller;

import br.com.veiculos.estoque.constantes.RabbitmqConstantes;
import br.com.veiculos.estoque.dto.EstoqueDTO;
import br.com.veiculos.estoque.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("estoque")
public class EstoqueController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @PutMapping
    private ResponseEntity atualizaEstoque(@RequestBody EstoqueDTO estoqueDTO){
        System.out.println("ESTOQUE" + estoqueDTO.codmodelo);
         rabbitmqService.enviaMensagem(RabbitmqConstantes.NOME_QUEUE, estoqueDTO);
        return  new ResponseEntity(HttpStatus.OK);

    }
}
