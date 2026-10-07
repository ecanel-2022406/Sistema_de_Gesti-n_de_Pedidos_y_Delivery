package com.delivery.fastorder.controller;

import com.delivery.fastorder.dto.ItemPedidoDTO;
import com.delivery.fastorder.model.Pedido;
import com.delivery.fastorder.model.enums.EstadoPedido;
import com.delivery.fastorder.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear/{clienteId}")
    public ResponseEntity<Pedido> crearPedido(@PathVariable Long clienteId, @RequestBody List<ItemPedidoDTO> itemsDto) {
        Pedido nuevoPedido = pedidoService.crearPedido(clienteId, itemsDto);
        return ResponseEntity.ok(nuevoPedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodos() {
        return ResponseEntity.ok(pedidoService.obtenerTodosLosPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obtenerPedidoPorId(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id, @RequestParam EstadoPedido nuevoEstado) {
        Pedido pedidoActualizado = pedidoService.actualizarEstadoPedido(id, nuevoEstado);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }
}