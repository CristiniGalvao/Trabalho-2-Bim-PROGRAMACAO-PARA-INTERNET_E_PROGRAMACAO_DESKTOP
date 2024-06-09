package com.example.ponto_de_venda_pdv.repository;

import com.example.ponto_de_venda_pdv.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
