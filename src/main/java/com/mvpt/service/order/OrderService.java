package com.mvpt.service.order;

import com.mvpt.model.Order;
import com.mvpt.model.dto.OrderDTO;
import com.mvpt.model.dto.OrderItemDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface OrderService extends IGeneralService<Order> {

    List<OrderDTO> getAllOrderDTOByTypeId(Long typeId);

    Optional<OrderDTO> getOrderDTOById(Long orderId);


}