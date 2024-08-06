package com.maltepuro.lagerlog.repository;

import com.maltepuro.lagerlog.model.ProdutosVendidos;
import com.maltepuro.lagerlog.model.ProdutosVendidosId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosVendidosRepository extends JpaRepository<ProdutosVendidos, ProdutosVendidosId> {


}

