package com.maltepuro.lagerlog.model;

import lombok.Data;
import java.sql.Timestamp;

@Data //criar getters, setters, toString ...
public class ItemProdutoVendido {
    private Long idVenda;
    private Long idProduto;
    private String descricaoProduto;
    private int quantidade;
    private Timestamp dataVenda;
    
    public ItemProdutoVendido(Long idVenda, Long idProduto, String descricaoProduto, int quantidade, Timestamp dataVenda) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.descricaoProduto = descricaoProduto;
        this.quantidade = quantidade;
        this.dataVenda = dataVenda;
        
    }
}

