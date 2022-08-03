package com.mvpt.service.cart;

import com.mvpt.model.Cart;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

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
    public Optional<CartDTO> getCartDTOById(Long id) {
        return cartRepository.getCartDTOById(id);
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
}
