package com.maltepuro.lagerlog.repository;

import com.maltepuro.lagerlog.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

//operacoes básicas de acesso a banco de dados
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsuario(String usuario);
}
