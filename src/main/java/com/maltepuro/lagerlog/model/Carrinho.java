package com.maltepuro.lagerlog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Carrinho {
    private Long id;
    private double preco;
    private int quantidade;
}
