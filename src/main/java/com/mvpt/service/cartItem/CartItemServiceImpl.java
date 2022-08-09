package com.mvpt.service.cartItem;

import com.mvpt.model.CartItem;
import com.mvpt.model.dto.CartItemDTO;
import com.mvpt.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public List<CartItemDTO> getAllCartDTOByDeletedIsFalse() {
        return cartItemRepository.getAllCartItemDTOByDeletedIsFalse();
    }

    @Override
    public List<CartItemDTO> getAllCartItemDTOByCartId(Long cartId) {
        return cartItemRepository.getAllCartItemDTOByCartId(cartId);
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public Optional<CartItemDTO> getCartItemDTOById(Long id) {
        return cartItemRepository.getCartItemDTOById(id);
    }

    @Override
    public Optional<CartItemDTO> getCartItemDTOByProductId(Long productId) {
        return cartItemRepository.getCartItemDTOByProductId(productId);
    }

    @Override
    public Optional<CartItemDTO> getCartItemDTOByCartIdAndProductId(Long productId, Long carId) {
        return cartItemRepository.getCartItemDTOByCartIdAndProductId(productId, carId);
    }

    @Override
    public CartItem getById(Long id) {
        return cartItemRepository.getById(id);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void remove(Long id) {
        cartItemRepository.deleteById(id);
    }

}
