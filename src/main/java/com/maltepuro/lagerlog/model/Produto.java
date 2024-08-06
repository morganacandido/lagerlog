package com.maltepuro.lagerlog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_produto")
    private Long id;
    private String descricao;
    private String marca;
    private String fornecedor;
    private String unidade;
    private double quantidade;
    private double preco;
    private int pontoSuprimento;
    private int tickets;
    private String categoria;
    private int status;

}
