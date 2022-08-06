package com.mvpt.service.orderItem;

import com.mvpt.model.OrderItem;
import com.mvpt.model.dto.OrderItemDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface OrderItemService extends IGeneralService<OrderItem> {

    List<OrderItemDTO> getAllOrderItemDTOByDeletedIsFalse();

    Optional<OrderItemDTO> getOrderItemDTOById(Long id);

    List<OrderItemDTO> getAllOrderItemDTOByOrderId(Long orderId);

}
