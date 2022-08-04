package com.mvpt.service.cart;

import com.mvpt.model.Cart;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.model.dto.CartItemDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface CartService extends IGeneralService<Cart> {
    List<CartDTO> getAllCartDTOByDeletedIsFalse();

    Optional<CartDTO> getCartDTOById(Long id);

    Optional<CartDTO> getCartDTOByUserId(Long userId);

    Optional<CartDTO> getCartDTOByTypeId(Long typeId);

    Optional<CartDTO> incrementGrandTotalAndQuantityTotal(CartItemDTO cartItemDTO);

    Optional<CartDTO> reduceGrandTotalAndQuantityTotal(CartItemDTO cartItemDTO);


}
