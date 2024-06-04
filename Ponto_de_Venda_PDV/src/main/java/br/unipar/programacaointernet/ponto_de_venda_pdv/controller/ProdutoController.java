package br.unipar.programacaointernet.ponto_de_venda_pdv.controller;

import br.unipar.programacaointernet.ponto_de_venda_pdv.model.Produto;
import br.unipar.programacaointernet.ponto_de_venda_pdv.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {
    private final ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService){this.produtoService = produtoService;}
@GetMapping
    public ResponseEntity<List<Produto>> findAll(){return ResponseEntity.ok(this.produtoService.findAll());}

}
