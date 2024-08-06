package com.maltepuro.lagerlog.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class ProdutosVendidosId implements Serializable {

    @Column(name = "cod_venda")
    private Long codVenda;
    @Column(name = "cod_produto")
    private Long codProduto;

}
