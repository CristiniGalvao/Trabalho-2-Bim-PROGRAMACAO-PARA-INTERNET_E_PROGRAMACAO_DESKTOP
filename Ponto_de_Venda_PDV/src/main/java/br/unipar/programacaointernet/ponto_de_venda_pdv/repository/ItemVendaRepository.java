package br.unipar.programacaointernet.ponto_de_venda_pdv.repository;

import br.unipar.programacaointernet.ponto_de_venda_pdv.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer> {
}
