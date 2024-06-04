package br.unipar.programacaointernet.ponto_de_venda_pdv.service;

import br.unipar.programacaointernet.ponto_de_venda_pdv.model.ItemVenda;
import br.unipar.programacaointernet.ponto_de_venda_pdv.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemVendaService {
    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public List<ItemVenda> findAll() {
        return itemVendaRepository.findAll();
    }

    public ItemVenda save(ItemVenda itemVenda) {
        return itemVendaRepository.save(itemVenda);
    }


}
