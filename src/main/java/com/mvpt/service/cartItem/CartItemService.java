package com.mvpt.service.cartItem;

import com.mvpt.model.CartItem;
import com.mvpt.model.dto.CartItemDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface CartItemService extends IGeneralService<CartItem> {
    List<CartItemDTO> getAllCartDTOByDeletedIsFalse();

    Optional<CartItemDTO> getCartDTOById(Long id);

    Optional<CartItemDTO> getCartItemDTOByProductId(Long id);

}
