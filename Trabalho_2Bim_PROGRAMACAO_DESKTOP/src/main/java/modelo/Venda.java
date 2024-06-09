/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Gabriella
 */
public class Venda {
    private Cliente cliente;
    private List<ItemVenda> itensVenda;
    private Integer id;
    private BigDecimal totalVenda;
    private String observacoes;

    public Venda(Cliente cliente, List<ItemVenda> itensVenda,Integer id ,BigDecimal totalVenda, String observacoes) {
        this.cliente = cliente;
        this.itensVenda = itensVenda;
        this.id = id;
        this.totalVenda = totalVenda;
        this.observacoes = observacoes;
    }

    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(BigDecimal totalVenda) {
        this.totalVenda = totalVenda;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    
}
