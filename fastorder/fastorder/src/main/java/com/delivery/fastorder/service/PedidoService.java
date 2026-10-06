package com.delivery.fastorder.service;

import com.delivery.fastorder.dto.ItemPedidoDTO;
import com.delivery.fastorder.model.*;
import com.delivery.fastorder.model.enums.EstadoPedido;
import com.delivery.fastorder.repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Transactional
    public Pedido crearPedido(Long clienteId, List<ItemPedidoDTO> itemsDto) {
        Usuario cliente = usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido.setCostoEnvio(20.00);

        double montoTotalProductos = 0.0;
        List<DetallePedido> detalles = new ArrayList<>();

        for (ItemPedidoDTO itemDto : itemsDto) {
            Producto producto = productoRepository.findById(itemDto.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < itemDto.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - itemDto.getCantidad());
            productoRepository.save(producto);

            double subtotal = producto.getPrecio() * itemDto.getCantidad();
            montoTotalProductos += subtotal;

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(itemDto.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(subtotal);

            detalles.add(detalle);
        }

        pedido.setMontoTotal(montoTotalProductos + pedido.getCostoEnvio());
        pedido.setDetalles(detalles);

        return pedidoRepository.save(pedido);
    }
}