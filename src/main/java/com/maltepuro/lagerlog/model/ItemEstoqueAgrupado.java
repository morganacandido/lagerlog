package com.maltepuro.lagerlog.model;

import lombok.Data;

@Data //criar getters, setters, toString ...
public class ItemEstoqueAgrupado {
    private Produto produto;
    private double quantidade;

    public ItemEstoqueAgrupado(Produto produto, double quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
}

