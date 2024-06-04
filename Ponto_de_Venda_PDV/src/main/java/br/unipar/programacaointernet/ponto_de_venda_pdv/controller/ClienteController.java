package br.unipar.programacaointernet.ponto_de_venda_pdv.controller;

import br.unipar.programacaointernet.ponto_de_venda_pdv.model.Cliente;
import br.unipar.programacaointernet.ponto_de_venda_pdv.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){this.clienteService = clienteService;}
    @GetMapping(path = "/api/cliente")
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok(this.clienteService.findAll());}
    @PostMapping(path = "/api/clientes")
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }
}
