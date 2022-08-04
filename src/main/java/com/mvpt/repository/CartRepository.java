package com.mvpt.repository;

import com.mvpt.model.Cart;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.CartDTO (" +
                "c.id, " +
                "c.grandTotal, " +
                "c.quantityTotal, " +
                "c.user, " +
                "c.type, " +
                "c.situation," +
                "c.unit) " +
                "FROM Cart c " +
            "WHERE c.deleted = false ")
    List<CartDTO> getAllCartDTOByDeletedIsFalse();

    @Query("SELECT NEW com.mvpt.model.dto.CartDTO (" +
                "c.id, " +
                "c.grandTotal, " +
                "c.quantityTotal, " +
                "c.user, " +
                "c.type, " +
                "c.situation," +
                "c.unit) " +
            "FROM Cart c " +
            "WHERE c.id = ?1 ")
    Optional<CartDTO> getCartDTOById(Long id);

    @Query("SELECT NEW com.mvpt.model.dto.CartDTO (" +
                "c.id, " +
                "c.grandTotal, " +
                "c.quantityTotal, " +
                "c.user, " +
                "c.type, " +
                "c.situation," +
                "c.unit) " +
            "FROM Cart c " +
            "WHERE c.user.id = ?1 ")
    Optional<CartDTO> getCartDTOByUserId(Long id);
}
