package com.example.ponto_de_venda_pdv.repository;

import com.example.ponto_de_venda_pdv.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
