package com.mvpt.controller.rest;

import com.mvpt.model.*;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.model.dto.CartItemDTO;
import com.mvpt.repository.TypeRepository;
import com.mvpt.service.cart.CartService;
import com.mvpt.service.cartItem.CartItemService;
import com.mvpt.service.product.ProductService;
import com.mvpt.service.situation.SituationService;
import com.mvpt.service.type.TypeService;
import com.mvpt.service.user.UserService;
import com.mvpt.utils.AppUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartRestController {

    @Autowired
    CartService cartService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @Autowired
    TypeService typeService;

    @Autowired
    SituationService situationService;

    @Autowired
    ProductService productService;

    @Autowired
    AppUtils appUtils;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody CartItemDTO cartItemDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Long currentCartId = Long.valueOf(cartItemDTO.getCart().getId());
        Long currentCartItemId = Long.valueOf(cartItemDTO.getId());

        Optional<CartDTO> currentCartDTO = cartService.getCartDTOById(currentCartId);
        Optional<CartItemDTO> currentCartItemDTO = cartItemService.getCartDTOById(currentCartItemId);

        if (!currentCartDTO.isPresent()) {
            Cart newCart = new Cart();

            Optional<User> user = userService.findById(Long.valueOf(cartItemDTO.getCart().getUser().getId()));
            Optional<Type> type = typeService.findById(Long.valueOf(cartItemDTO.getCart().getType().getId()));

            newCart.setId(0L);
            newCart.setGrandTotal(BigDecimal.valueOf(0L));
            newCart.setQuantityTotal(Integer.parseInt(cartItemDTO.getQuantity()));
            newCart.setUser(user.get());
            newCart.setType(type.get());

            cartService.save(newCart);

            Optional<Product> product = productService.findById(cartItemDTO.toCartItem().getProduct().getId());

            cartItemDTO.setProduct(product.get().toProductDTO());
            cartItemDTO.getCart().setId(String.valueOf(newCart.getId()));

            cartItemService.save(cartItemDTO.toCartItem());

        }else {
            if (currentCartItemDTO.isPresent()) {
                Cart currentCart = currentCartDTO.get().toCart();
                CartItem cartItem = cartItemDTO.toCartItem();

                BigDecimal price = cartItemDTO.toCartItem().getTotalPrice();
                int quantity = cartItemDTO.toCartItem().getQuantity();

                cartItem.setPrice(price);
                cartItem.setQuantity(quantity);

                cartItemService.save(cartItem);

                BigDecimal grandTotal = currentCart.getGrandTotal().add(price);
                int quantityTotal = currentCart.getQuantityTotal() + quantity;

                currentCart.setGrandTotal(grandTotal);
                currentCart.setQuantityTotal(quantityTotal);

                cartService.save(currentCart);

            }else {
                Optional<Product> product = productService.findById(cartItemDTO.toCartItem().getProduct().getId());

                cartItemDTO.setProduct(product.get().toProductDTO());
                cartItemDTO.getCart().setId(String.valueOf(currentCartDTO.get().getId()));

                cartItemService.save(cartItemDTO.toCartItem());
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);


    }
}
