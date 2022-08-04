package com.mvpt.service.cart;

import com.mvpt.model.Cart;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.model.dto.CartItemDTO;
import com.mvpt.repository.CartItemRepository;
import com.mvpt.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public List<CartDTO> getAllCartDTOByDeletedIsFalse() {
        return cartRepository.getAllCartDTOByDeletedIsFalse();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Optional<CartDTO> getCartDTOByTypeIdAndUserId(Long typeId, Long userId) {
        return cartRepository.getCartDTOByTypeIdAndUserId(typeId, userId);
    }

    @Override
    public Optional<CartDTO> getCartDTOById(Long id) {
        return cartRepository.getCartDTOById(id);
    }

    @Override
    public Optional<CartDTO> getCartDTOByUserId(Long userId) {
        return cartRepository.getCartDTOByUserId(userId);
    }

    @Override
    public Optional<CartDTO> getCartDTOByTypeId(Long typeId) {
        return cartRepository.getCartDTOByTypeId(typeId);
    }

    @Override
    public Cart getById(Long id) {
        return cartRepository.getById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart incrementGrandTotalAndQuantityTotal(CartItemDTO cartItemDTO) {

        BigDecimal totalPrice = new BigDecimal(cartItemDTO.getTotalPrice());
        int quantity = Integer.parseInt(cartItemDTO.getQuantity());

        Optional<CartDTO> newCartDTO = cartRepository.getCartDTOById(cartItemDTO.toCartItem().getCart().getId());

        BigDecimal grandTotal = new BigDecimal(newCartDTO.get().getGrandTotal());
        int quantityTotal = Integer.parseInt(newCartDTO.get().getQuantityTotal());

        newCartDTO.get().setGrandTotal(String.valueOf(grandTotal.add(totalPrice)));
        newCartDTO.get().setQuantityTotal(String.valueOf(quantityTotal + quantity));

        return cartRepository.save(newCartDTO.get().toCart());
    }

    @Override
    public Cart reduceGrandTotalAndQuantityTotal(CartItemDTO cartItemDTO) {

        BigDecimal totalPrice = new BigDecimal(cartItemDTO.getTotalPrice());
        int quantity = Integer.parseInt(cartItemDTO.getQuantity());

        Optional<CartDTO> newCartDTO = cartRepository.getCartDTOById(cartItemDTO.toCartItem().getCart().getId());

        BigDecimal grandTotal = new BigDecimal(newCartDTO.get().getGrandTotal());
        int quantityTotal = Integer.parseInt(newCartDTO.get().getQuantityTotal());

        newCartDTO.get().setGrandTotal(String.valueOf(grandTotal.subtract(totalPrice)));
        newCartDTO.get().setQuantityTotal(String.valueOf(quantityTotal - quantity));

        return cartRepository.save(newCartDTO.get().toCart());
    }
}
