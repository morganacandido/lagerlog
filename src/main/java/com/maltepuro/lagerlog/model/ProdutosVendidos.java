package com.maltepuro.lagerlog.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="produtos_vendidos")
public class ProdutosVendidos {
    @EmbeddedId
    private ProdutosVendidosId id;

    @Column(name = "quantidade")
    private int quantidade;

}