package com.example.ponto_de_venda_pdv.controller;

import com.example.ponto_de_venda_pdv.model.Venda;
import com.example.ponto_de_venda_pdv.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @GetMapping("/vendas")
    public List<Venda> findAll() {
        return vendaService.findAll();
    }

    @PostMapping
    public Venda save(@RequestBody Venda venda) {
        return vendaService.save(venda);
    }
}
