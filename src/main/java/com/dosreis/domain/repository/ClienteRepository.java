package com.dosreis.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dosreis.domain.entity.Cliente;
import com.dosreis.rest.dto.ClientecomPedidosDTO;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query(value = "select c from Cliente c where c.nome like %:nome% ")
	List<Cliente> buscaPorNome( @Param("nome") String nome);

	@Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
	Cliente findClienteFecthPedidos( @Param("id") Integer id);
	
	@Query(" select c from Cliente c where c.cpf = :cpf")
	Cliente buscaPorCpf(@Param("cpf")String cpf);
	
	//@Query(" select c from Cliente c join c.pedidos p on c.id = p.idCliente where p.idCliente = :id ")
	//List<Cliente> buscarPedidos(@Param("id") Integer id);
	
	@Query(value = "select * from tb_cliente join tb_pedido on tb_cliente.id = tb_pedido.id_cliente where tb_pedido.id_cliente = :id ", nativeQuery = true)
	List<ClientecomPedidosDTO> buscarPedidos(@Param("id") Integer id);
}
