package com.maltepuro.lagerlog.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_estoque")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cod_produto", nullable = false)
    private Produto produto;

    private String tipo;
    private double quantidade;
    private LocalDateTime dataCadastro;
    private String observacao;

}