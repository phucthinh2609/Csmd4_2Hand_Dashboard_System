package com.mvpt.repository;

import com.mvpt.model.CartItem;
import com.mvpt.model.OrderItem;
import com.mvpt.model.dto.CartItemDTO;
import com.mvpt.model.dto.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.OrderItemDTO (" +
                "ci.id, " +
                "ci.price, " +
                "ci.quantity, " +
                "ci.totalPrice, " +
                "ci.order, " +
                "ci.product) " +
            "FROM OrderItem ci " +
            "WHERE ci.deleted = false ")
    List<OrderItemDTO> getAllOrderItemDTOByDeletedIsFalse();


    @Query("SELECT NEW com.mvpt.model.dto.OrderItemDTO (" +
                "ci.id, " +
                "ci.price, " +
                "ci.quantity, " +
                "ci.totalPrice, " +
                "ci.order, " +
                "ci.product) " +
            "FROM OrderItem ci " +
            "WHERE ci.id = ?1 ")
    Optional<OrderItemDTO> getOrderItemDTOById(Long id);

}
