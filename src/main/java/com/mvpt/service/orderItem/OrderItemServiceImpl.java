package com.mvpt.service.orderItem;

import com.mvpt.model.OrderItem;
import com.mvpt.model.dto.OrderItemDTO;
import com.mvpt.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public List<OrderItemDTO> getAllOrderItemDTOByDeletedIsFalse() {
        return orderItemRepository.getAllOrderItemDTOByDeletedIsFalse();
    }

    @Override
    public List<OrderItemDTO> getAllOrderItemDTOByOrderId(Long orderId) {
        return orderItemRepository.getAllOrderItemDTOByOrderId(orderId);
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public OrderItem getById(Long id) {
        return orderItemRepository.getById(id);
    }

    @Override
    public Optional<OrderItemDTO> getOrderItemDTOById(Long id) {
        return orderItemRepository.getOrderItemDTOById(id);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void remove(Long id) {
        orderItemRepository.deleteById(id);
    }
}
