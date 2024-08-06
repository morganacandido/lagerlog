package com.maltepuro.lagerlog.model;

import java.sql.Timestamp;

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
@Entity(name="vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_venda")
    private Long id;
    @Column(name = "cod_usuario")
    private Long codUsuario;
    @Column(name = "data_venda")
    private Timestamp dataVenda;
    @Column(name = "valor_total")
    private double valorVenda;

}
