package br.unipar.programacaointernet.ponto_de_venda_pdv.repository;

import br.unipar.programacaointernet.ponto_de_venda_pdv.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
}
