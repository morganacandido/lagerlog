package com.maltepuro.lagerlog.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maltepuro.lagerlog.model.ProdutosVendidos;
import com.maltepuro.lagerlog.model.Venda;
import com.maltepuro.lagerlog.model.ItemProdutoVendido;
import com.maltepuro.lagerlog.model.Produto;
import com.maltepuro.lagerlog.repository.ProdutosVendidosRepository;
import com.maltepuro.lagerlog.repository.ProdutoRepository;
import com.maltepuro.lagerlog.repository.VendaRepository;


@Controller
@RequestMapping("/venda")
public class VendasController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutosVendidosRepository produtosVendidosRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping("/listar")
    public String listarVendas(Model model){
    List<Venda> vendas = vendaRepository.findAll();
    model.addAttribute("vendas", vendas);

    return "listarVendas";
    }

    @RequestMapping("/produtos")
    public String listarProdutosVendidos(Model model) {
    
    List<ProdutosVendidos> produtosVendidos = produtosVendidosRepository.findAll();
    List<Produto> produtos = produtoRepository.findAll();
    List<Venda> vendas = vendaRepository.findAll(); 
    
    // Construir um mapa de ID de produto para descrição
    Map<Long, String> descricaoPorProduto = produtos.stream()
            .collect(Collectors.toMap(
                Produto::getId, 
                Produto::getDescricao
            ));

    // Construir um mapa de ID de venda para data de venda
    Map<Long, Timestamp> dataVendaPorId = vendas.stream()
            .collect(Collectors.toMap(
                Venda::getId,
                Venda::getDataVenda
        ));
    
    // Criar uma lista de ProdutosVendidos com descrição do produto
    List<ItemProdutoVendido> produtosVendidosComDescricao = produtosVendidos.stream()
            .map(pv -> new ItemProdutoVendido(
                    pv.getId().getCodVenda(),
                    pv.getId().getCodProduto(),
                    descricaoPorProduto.get(pv.getId().getCodProduto()), // Usar o mapa para obter a descrição
                    pv.getQuantidade(),
                    dataVendaPorId.get(pv.getId().getCodVenda()) // Usar o mapa para obter a data da venda
            ))
            .collect(Collectors.toList());

    model.addAttribute("produtosVendidos", produtosVendidosComDescricao);
    return "listarProdutosVendidos";
    }   
}