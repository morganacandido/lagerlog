package com.maltepuro.lagerlog.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maltepuro.lagerlog.model.Estoque;
import com.maltepuro.lagerlog.model.ItemEstoqueAgrupado;
import com.maltepuro.lagerlog.model.Produto;
import com.maltepuro.lagerlog.repository.EstoqueRepository;
import com.maltepuro.lagerlog.repository.ProdutoRepository;


@Controller
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/estoque")
    public String getEstoque(Model model) {
        List<Estoque> estoque = estoqueRepository.findAll();
        
        Map<Produto, Double> quantidadePorProduto = estoque.stream()
                .collect(Collectors.toMap(
                        Estoque::getProduto,
                        Estoque::getQuantidade,
                        (quantidade1, quantidade2) -> quantidade1 + quantidade2
                ));
        
        List<ItemEstoqueAgrupado> estoqueAgrupado = quantidadePorProduto.entrySet().stream()
                .map(entry -> new ItemEstoqueAgrupado(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        model.addAttribute("estoqueAgrupado", estoqueAgrupado);
        return "listarEstoque";
    }

    
    @GetMapping("/estoque/registros")
    public String getRegistrosEstoque(Model model) {
        List<Estoque> estoque = estoqueRepository.findAll();
        Estoque[] arrayEstoque = estoque.toArray(new Estoque[0]);
        model.addAttribute("estoque", arrayEstoque);
        return "listarRegistrosEstoque";
    }

    @GetMapping("/estoque/cadastrar")
    public String entradaEstoque(Model model){
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "cadastrarEstoque";
    }

   
    @PostMapping("/estoque/cadastrar")
    public String cadastrarEstoque(@RequestParam String produto,
        @RequestParam String quantidade, @RequestParam String observacao, 
        RedirectAttributes redirectAttributes) {
        Estoque e = new Estoque();

        Produto produtoEntity = produtoRepository.findById(Long.parseLong(produto))
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        e.setProduto(produtoEntity);
        e.setTipo("ENTRADA");
        e.setQuantidade(Double.parseDouble(quantidade));
        e.setObservacao(observacao);
        e.setDataCadastro(LocalDateTime.now());
        estoqueRepository.save(e);
        System.out.println("Entrada cadastrada com sucesso!");
        redirectAttributes.addFlashAttribute("mensagem", "Entrada de estoque cadastrada com sucesso!");
        return "redirect:/estoque";
    }

    @GetMapping("/estoque/ajustar")
    public String ajusteEstoque(Model model){
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "ajustarEstoque";
    }

    @PostMapping("/estoque/ajustar")
    public String ajustarEstoque(@RequestParam String produto,
        @RequestParam String quantidade, @RequestParam String observacao, 
        RedirectAttributes redirectAttributes) {
        Estoque e = new Estoque();

        Produto produtoEntity = produtoRepository.findById(Long.parseLong(produto))
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        e.setProduto(produtoEntity);
        e.setTipo("AJUSTE");
        e.setQuantidade(-Double.parseDouble(quantidade));
        e.setObservacao(observacao);
        e.setDataCadastro(LocalDateTime.now());
        estoqueRepository.save(e);
        System.out.println("Ajuste realizado com sucesso!");
        redirectAttributes.addFlashAttribute("mensagem", "Ajuste de estoque realizado com sucesso!");
        return "redirect:/estoque";
    }
}