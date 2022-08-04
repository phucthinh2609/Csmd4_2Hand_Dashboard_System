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

    Optional<CartDTO> getCartDTOByTypeIdAndUserId(Long typeId, Long userId);

    Optional<CartDTO> getCartDTOByUserId(Long userId);

    Optional<CartDTO> getCartDTOByTypeId(Long typeId);

    Cart incrementGrandTotalAndQuantityTotal(CartItemDTO cartItemDTO);

    Cart reduceGrandTotalAndQuantityTotal(CartItemDTO cartItemDTO);


}
