package com.mvpt.repository;

import com.mvpt.model.CartItem;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.model.dto.CartItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.CartItemDTO (" +
                "ci.id, " +
                "ci.price, " +
                "ci.quantity, " +
                "ci.totalPrice, " +
                "ci.cart, " +
                "ci.product) " +
            "FROM CartItem ci " +
            "WHERE ci.deleted = false ")
    List<CartItemDTO> getAllCartDTOByDeletedIsFalse();

    @Query("SELECT NEW com.mvpt.model.dto.CartItemDTO (" +
                "ci.id, " +
                "ci.price, " +
                "ci.quantity, " +
                "ci.totalPrice, " +
                "ci.cart, " +
                "ci.product) " +
            "FROM CartItem ci " +
            "WHERE ci.id = ?1 ")
    Optional<CartItemDTO> getCartDTOById(Long id);
}
