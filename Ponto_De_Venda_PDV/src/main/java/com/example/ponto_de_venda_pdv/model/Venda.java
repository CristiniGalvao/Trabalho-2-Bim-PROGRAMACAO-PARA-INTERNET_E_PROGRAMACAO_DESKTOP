package com.example.ponto_de_venda_pdv.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Venda { @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

    private String observacoes;

    private Timestamp data;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "fk_cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<ItemVenda> itens = new ArrayList<>();
}