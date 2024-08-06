package com.maltepuro.lagerlog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.maltepuro.lagerlog.model.Carrinho;
import com.maltepuro.lagerlog.model.ProdutosVendidos;
import com.maltepuro.lagerlog.model.ProdutosVendidosId;
import com.maltepuro.lagerlog.model.Estoque;
import com.maltepuro.lagerlog.model.Produto;
import com.maltepuro.lagerlog.model.Venda;
import com.maltepuro.lagerlog.repository.EstoqueRepository;
import com.maltepuro.lagerlog.repository.ProdutosVendidosRepository;
import com.maltepuro.lagerlog.repository.VendaRepository;
import com.maltepuro.lagerlog.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/venda")
public class CarrinhoController {

    @Autowired // injetar repositório automaticamente, em vez de criar uma instância dele manualmente.
    private VendaRepository vendaRepository; // utilizar a classe de acesso ao banco de dados
    @Autowired
    private ProdutosVendidosRepository produtosVendidosRepository; // utilizar a classe de acesso ao banco de dados

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizarVenda(@RequestBody List<Carrinho> produtos) {
        System.out.println("Recebido " + produtos.size() + " produtos.");
        try {
            BigDecimal valorTotal = calcularValorTotal(produtos);

            Venda venda = new Venda();
            // LocalDate dataAtual = LocalDate.now();
            // venda.setDataVenda(Date.valueOf(dataAtual));
            LocalDateTime dataHoraAtual = LocalDateTime.now();
            venda.setDataVenda(Timestamp.valueOf(dataHoraAtual));
            venda.setValorVenda(valorTotal.doubleValue());
            venda.setCodUsuario(1L); // teste com usuário com ID 1
            vendaRepository.save(venda);

            Long codVenda = venda.getId();
            System.out.println("Venda salva com ID: " + codVenda);
            
            for (Carrinho produto : produtos) {
                // Criando a chave composta
                ProdutosVendidosId produtosVendidosId = new ProdutosVendidosId();
                produtosVendidosId.setCodVenda(codVenda); // Definindo o código da venda
                produtosVendidosId.setCodProduto(produto.getId()); // Definindo o código do produto
            
                // Criando a entidade ProdutosVendidos
                ProdutosVendidos produtosVendidos = new ProdutosVendidos();
                produtosVendidos.setId(produtosVendidosId); // Definindo a chave composta
                produtosVendidos.setQuantidade(produto.getQuantidade()); // Definindo a quantidade do produto
            
                // Salvando no repositório
                produtosVendidosRepository.save(produtosVendidos);

               // Buscando o produto pelo ID
                Produto produtoEntity = produtoRepository.findById(produto.getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produto.getId()));

                // Criando nova entrada no estoque
                Estoque baixaEstoque = new Estoque();
                baixaEstoque.setProduto(produtoEntity);
                baixaEstoque.setTipo("VENDA");
                baixaEstoque.setQuantidade(-produto.getQuantidade());
                baixaEstoque.setDataCadastro(LocalDateTime.now());
                baixaEstoque.setObservacao("Venda " + codVenda);
                estoqueRepository.save(baixaEstoque);
            }

            return ResponseEntity.ok("Venda finalizada com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao finalizar venda: " + e.getMessage());
        }
    }

    private BigDecimal calcularValorTotal(List<Carrinho> produtos) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (Carrinho produto : produtos) {
            BigDecimal precoUnitario = BigDecimal.valueOf(produto.getPreco());
            BigDecimal quantidade = BigDecimal.valueOf(produto.getQuantidade());
            BigDecimal subtotal = precoUnitario.multiply(quantidade);
            valorTotal = valorTotal.add(subtotal);
        }
        return valorTotal;
    }
}


