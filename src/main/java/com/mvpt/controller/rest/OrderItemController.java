package com.mvpt.controller.rest;

import com.mvpt.exception.DataInputException;
import com.mvpt.model.OrderItem;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.model.dto.CartItemDTO;
import com.mvpt.model.dto.OrderDTO;
import com.mvpt.model.dto.OrderItemDTO;
import com.mvpt.service.cart.CartService;
import com.mvpt.service.cartItem.CartItemService;
import com.mvpt.service.order.OrderService;
import com.mvpt.service.orderItem.OrderItemService;
import com.mvpt.service.product.ProductService;
import com.mvpt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    OrderService orderService;

    @Autowired
    AppUtils appUtils;


    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderAndOrderItemsByOrderId(@PathVariable Long orderId) {
        Map<String, Object> result = new HashMap<>();

        List<OrderItemDTO> orderItemDTOList = orderItemService.getAllOrderItemDTOByOrderId(orderId);

        Optional<OrderDTO> orderDTO = orderService.getOrderDTOById(orderId);

        result.put("orderItemList", orderItemDTOList);
        result.put("order", orderDTO.get());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
