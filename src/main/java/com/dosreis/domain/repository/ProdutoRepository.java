package com.dosreis.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dosreis.domain.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query(value = "select p from Produto p where p.preco between :min and :max")
	List<Produto> buscaPorFaixaPreco( @Param("min") Double min, @Param("max") Double max);
	}

