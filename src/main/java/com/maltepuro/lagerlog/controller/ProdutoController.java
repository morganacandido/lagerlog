package com.maltepuro.lagerlog.controller;

import com.maltepuro.lagerlog.model.Produto;
import com.maltepuro.lagerlog.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public String incluir(
            @RequestParam String descricao,
            @RequestParam String marca,
            @RequestParam String fornecedor,
            @RequestParam String unidade,
            @RequestParam double quantidade,
            @RequestParam double preco,
            @RequestParam int pontoSuprimento,
            @RequestParam int tickets,
            @RequestParam String categoria,
            @RequestParam int status,
            RedirectAttributes redirectAttributes) {
        Produto p = new Produto();
        p.setDescricao(descricao);
        p.setMarca(marca);
        p.setFornecedor(fornecedor);
        p.setUnidade(unidade);
        p.setQuantidade(quantidade);
        p.setPreco(preco);
        p.setPontoSuprimento(pontoSuprimento);
        p.setTickets(tickets);
        p.setCategoria(categoria);
        p.setStatus(status);
        produtoRepository.save(p);
        System.out.println("Produto cadastrado com sucesso!");
        redirectAttributes.addFlashAttribute("mensagem", "Produto cadastrado com sucesso!");
        return "redirect:/produto/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        List<Produto> produtos = produtoRepository.findAll();
        Produto[] arrayProdutos = produtos.toArray(new Produto[0]);
        model.addAttribute("listaProdutos", arrayProdutos);
        return "listarProdutos";
    }

    @PutMapping("/alterar")
    public ResponseEntity<Void> alterar(@RequestBody Produto produto){
        produtoRepository.save(produto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/editar")
    public String editar(
            @RequestParam String id,
            @RequestParam String descricao,
            @RequestParam String marca,
            @RequestParam String fornecedor,
            @RequestParam String unidade,
            @RequestParam double quantidade,
            @RequestParam double preco,
            @RequestParam int pontoSuprimento,
            @RequestParam int tickets,
            @RequestParam String categoria,
            @RequestParam int status,
            RedirectAttributes redirectAttributes) {

        Produto p = new Produto();
        p.setId(Long.parseLong(id));
        p.setDescricao(descricao);
        p.setMarca(marca);
        p.setFornecedor(fornecedor);
        p.setUnidade(unidade);
        p.setQuantidade(quantidade);
        p.setPreco(preco);
        p.setPontoSuprimento(pontoSuprimento);
        p.setTickets(tickets);
        p.setCategoria(categoria);
        p.setStatus(status);
        produtoRepository.save(p);
        System.out.println("Produto editado com sucesso!");
        redirectAttributes.addFlashAttribute("mensagem", "Produto editado com sucesso!");
        return "redirect:/produto/listar";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            produtoRepository.deleteById(id);
            System.out.println("Produto excluido com sucesso!");
            return ResponseEntity.ok().build(); // Retorna 200 OK
        } catch (Exception e) {
            System.out.println("Erro ao excluir o produto!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir produto: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarId(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cadastrar")
    public String criarProduto(Model model){
        return "cadastrarProduto";
    }

    @GetMapping("/editar/{id}")
    public String carregarProduto(@PathVariable Long id, Model model) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            model.addAttribute("produto", produto);;
            return "editarProduto";
        } else {
            return "error";
        }
    }

}
