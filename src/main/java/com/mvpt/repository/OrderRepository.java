package com.mvpt.repository;

import com.mvpt.model.Order;
import com.mvpt.model.dto.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.OrderDTO (" +
                "o.id, " +
                "o.grandTotal, " +
                "o.quantityTotal, " +
                "o.user, " +
                "o.type, " +
                "o.situation," +
                "o.unit, " +
                "o.createdAt," +
                "o.createdBy," +
                "o.updatedAt," +
                "o.updatedBy)" +
            "FROM Order o " +
            "WHERE o.type.id = ?1")
    List<OrderDTO> getAllOrderDTOByTypeId(Long typeId);

    @Query("SELECT NEW com.mvpt.model.dto.OrderDTO (" +
                "o.id, " +
                "o.grandTotal, " +
                "o.quantityTotal, " +
                "o.user, " +
                "o.type, " +
                "o.situation," +
                "o.unit, " +
                "o.createdAt," +
                "o.createdBy," +
                "o.updatedAt," +
                "o.updatedBy)" +
            "FROM Order o " +
            "WHERE o.id = ?1 ")
    Optional<OrderDTO> getOrderDTOById(Long orderId);

}
