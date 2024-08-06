package com.maltepuro.lagerlog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor  //criar construtor sem argumentos
@AllArgsConstructor //criar construtor com todos argumentos
@Data //criar getters, setters, toString ...
@Entity // tabela
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_usuario")
    private Long id;
    private String usuario;
    private String nome;
    private String senha;
    private String grupo;
    private boolean status;

    public boolean getStatus() {
        return status;
    }
    
    /*
    @NotNull
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.")
    private String nome;

    @NotNull(message = "A senha deve ser informada.")
    private Long senha;

    @NotNull(message = "Por favor selecione o nível do usuário.")
    private Long nivel;
    */

}