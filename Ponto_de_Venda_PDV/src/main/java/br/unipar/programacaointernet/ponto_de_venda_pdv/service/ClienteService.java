package br.unipar.programacaointernet.ponto_de_venda_pdv.service;

import br.unipar.programacaointernet.ponto_de_venda_pdv.model.Cliente;
import br.unipar.programacaointernet.ponto_de_venda_pdv.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}
