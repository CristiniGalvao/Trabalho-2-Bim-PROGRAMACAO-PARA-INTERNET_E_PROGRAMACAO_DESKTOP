package com.example.ponto_de_venda_pdv.controller;

import com.example.ponto_de_venda_pdv.model.Venda;
import com.example.ponto_de_venda_pdv.service.VendaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class VendaWebController {
    private final VendaService vendaService;

    public VendaWebController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping("/api/vendas")
    public String findAllVendas(Model model) {
        List<Venda> vendas = vendaService.findAll();
        model.addAttribute("vendas", vendas);
        return "vendas";
    }

    @PostMapping(path = "/vendas/save")
    public String saveVenda(Venda venda) {
        vendaService.save(venda);
        return "redirect:/vendas";
    }
    @GetMapping("/vendas")
    public String getVendasFiltradasPorData(
            @RequestParam("dataInicial") LocalDate dataInicial,
            @RequestParam("dataFinal") LocalDate dataFinal,
            Model model) {
        List<Venda> vendas = vendaService.getVendasPorPeriodo(dataInicial, dataFinal);
        model.addAttribute("vendas", vendas);
        return "vendas"; // Nome do arquivo de template (vendas.html)
    }
}
