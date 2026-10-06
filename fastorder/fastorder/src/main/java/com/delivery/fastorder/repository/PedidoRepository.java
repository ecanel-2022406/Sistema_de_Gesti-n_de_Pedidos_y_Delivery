package com.delivery.fastorder.repository;

import com.delivery.fastorder.model.Pedido;
import com.delivery.fastorder.model.enums.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByEstado(EstadoPedido estado);
    List<Pedido> findByRepartidorIdOrEstado(Long repartidorId, EstadoPedido estado);
}