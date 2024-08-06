package com.maltepuro.lagerlog.repository;

import com.maltepuro.lagerlog.model.Produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByStatus(int status);
    
}

