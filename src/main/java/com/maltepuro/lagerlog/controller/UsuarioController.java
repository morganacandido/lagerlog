package com.maltepuro.lagerlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maltepuro.lagerlog.model.Usuario;
import com.maltepuro.lagerlog.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired //injetar repositório automaticamente, em vez de criar uma instância dele manualmente.
    private UsuarioRepository usuarioRepository; //utilizar a classe de acesso ao banco de dados
    
    @PostMapping
    public String incluir(@RequestParam String usuario,
                        @RequestParam String nome, 
                        @RequestParam String senha, 
                        @RequestParam String grupo,
                        RedirectAttributes redirectAttributes) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsuario(usuario);
        novoUsuario.setNome(nome);
        novoUsuario.setSenha(senha);
        novoUsuario.setGrupo(grupo);
        novoUsuario.setStatus(true);
        usuarioRepository.save(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "redirect:/usuario/listar";
    }


    @GetMapping("/listar")
    public String listar(Model model){
        List<Usuario> usuarios = usuarioRepository.findAll();
        Usuario[] arrayUsuarios = usuarios.toArray(new Usuario[0]);
        model.addAttribute("listaUsuarios", arrayUsuarios);
        return "listarUsuarios";
    }

    @PutMapping("/alterar")
    public ResponseEntity<Void> alterar(@RequestBody Usuario usuario){
        //pode fazer validacoes aqui
        usuarioRepository.save(usuario);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/editar")
    public String editar(@RequestParam String id, @RequestParam String usuario, @RequestParam String nome, 
        @RequestParam String senha, 
        @RequestParam String grupo,
        @RequestParam boolean status,
        RedirectAttributes redirectAttributes) {
        Usuario u = new Usuario();
        u.setId(Long.parseLong(id));
        u.setUsuario(usuario);
        u.setNome(nome);
        u.setSenha(senha);
        u.setGrupo(grupo);
        u.setStatus(status);
        usuarioRepository.save(u);
        System.out.println("Usuário editado com sucesso!");
        redirectAttributes.addFlashAttribute("mensagem", "Usuário editado com sucesso!");
        return "redirect:/usuario/listar";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            usuarioRepository.deleteById(id);
            System.out.println("Usuário excluido com sucesso!");
            return ResponseEntity.ok().build(); // Retorna 200 OK           
        } catch (Exception e) {
            System.out.println("Erro ao excluir o usuário!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuário: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarId(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
    
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cadastrar")
    public String criarUsuario(Model model){
        return "cadastrarUsuario";
    }
   
    @GetMapping("/editar/{id}")
    public String carregarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
    
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            model.addAttribute("usuario", usuario);;
            return "editarUsuario"; 
        } else {
            return "error";
        }
    }

}
