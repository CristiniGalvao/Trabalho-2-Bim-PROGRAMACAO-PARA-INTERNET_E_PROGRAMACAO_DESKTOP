package br.unipar.programacaointernet.ponto_de_venda_pdv.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantidade;

    private BigDecimal valor_unitario;

    private BigDecimal valor_total;

    @ManyToOne
    @JoinColumn(name = "fk_venda_id",nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "fk_produto_id",nullable = false)
    private Produto produto;

}
