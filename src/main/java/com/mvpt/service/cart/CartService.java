package com.mvpt.service.cart;

import com.mvpt.model.Cart;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface CartService extends IGeneralService<Cart> {
    List<CartDTO> getAllCartDTOByDeletedIsFalse();

    Optional<CartDTO> getCartDTOById(Long id);

    Optional<CartDTO> getCartDTOByUserId(Long id);

}
