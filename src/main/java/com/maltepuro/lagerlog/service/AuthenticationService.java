package com.maltepuro.lagerlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maltepuro.lagerlog.model.Usuario;
import com.maltepuro.lagerlog.repository.UsuarioRepository;

@Service
public class AuthenticationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean authentication(String usuario, String senha) {
        Usuario u = usuarioRepository.findByUsuario(usuario);

        if (u == null) {
            return false;
        } else if (senha.equals(u.getSenha()) && u.getStatus()) {
            return true;
        } else {
            return false;
        }
    }
}
