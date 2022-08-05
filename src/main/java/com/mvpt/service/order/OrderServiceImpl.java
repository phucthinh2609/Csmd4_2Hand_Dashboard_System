package com.mvpt.service.order;

import com.mvpt.model.Order;
import com.mvpt.model.dto.OrderDTO;
import com.mvpt.model.dto.OrderItemDTO;
import com.mvpt.repository.CartRepository;
import com.mvpt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public Optional<OrderDTO> getOrderDTOById(Long orderId) {
        return orderRepository.getOrderDTOById(orderId);
    }

    @Override
    public List<OrderDTO> getAllOrderDTOByTypeId(Long typeId) {
        return orderRepository.getAllOrderDTOByTypeId(typeId);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }
}
