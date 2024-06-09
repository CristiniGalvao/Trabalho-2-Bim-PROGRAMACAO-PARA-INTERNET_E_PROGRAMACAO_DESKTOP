package com.example.ponto_de_venda_pdv.repository;

import com.example.ponto_de_venda_pdv.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
