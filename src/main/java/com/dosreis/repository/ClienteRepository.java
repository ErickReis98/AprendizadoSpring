package com.dosreis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dosreis.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query(value = "select c from Cliente c where c.nome like %:nome% ")
	List<Cliente> buscaPorNome( @Param("nome") String nome);

	@Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
	Cliente findClienteFecthPedidos( @Param("id") Integer id);
	
}
