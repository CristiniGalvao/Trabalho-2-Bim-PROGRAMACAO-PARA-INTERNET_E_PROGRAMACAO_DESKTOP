package com.example.ponto_de_venda_pdv.service;

import com.example.ponto_de_venda_pdv.model.Venda;
import com.example.ponto_de_venda_pdv.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }


    public Venda save(Venda venda) {
        return vendaRepository.save(venda);
    }

    public List<Venda> getVendasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return vendaRepository.findByDataBetween(dataInicial, dataFinal);
    }

}
