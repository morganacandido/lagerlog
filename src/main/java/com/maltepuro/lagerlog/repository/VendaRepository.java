package com.maltepuro.lagerlog.repository;

import com.maltepuro.lagerlog.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

//operacoes básicas de acesso a banco de dados
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
