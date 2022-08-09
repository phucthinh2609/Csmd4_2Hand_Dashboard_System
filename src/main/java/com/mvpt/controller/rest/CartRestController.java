package com.mvpt.controller.rest;

import com.mvpt.exception.DataInputException;
import com.mvpt.model.*;
import com.mvpt.model.dto.*;
import com.mvpt.service.cart.CartService;
import com.mvpt.service.cartItem.CartItemService;
import com.mvpt.service.product.ProductService;
import com.mvpt.service.situation.SituationService;
import com.mvpt.service.type.TypeService;
import com.mvpt.service.unit.UnitService;
import com.mvpt.service.user.UserService;
import com.mvpt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    UnitService unitService;

    @Autowired
    ProductService productService;

    @Autowired
    AppUtils appUtils;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cart-items/import")
    public ResponseEntity<?> getAllCartItemImport(@RequestBody String userDTOId) {

        Map<String, Object> result = new HashMap<>();

        Long typeId = 1L;

        Optional<CartDTO> cartDTO = cartService.getCartDTOByTypeIdAndUserId(typeId, Long.valueOf(userDTOId));
        Long cartId = Long.parseLong(cartDTO.get().getId());

        List<CartItemDTO> cartItemDTO = cartItemService.getAllCartItemDTOByCartId(cartId);

        result.put("cartDTO", cartDTO.get());
        result.put("cartItemDTO", cartItemDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/cart-items/purchase")
    public ResponseEntity<?> getAllCartItemPurchase(@RequestBody String userDTOId) {

        Map<String, Object> result = new HashMap<>();

        Long typeId = 2L;

        Optional<CartDTO> cartDTO = cartService.getCartDTOByTypeIdAndUserId(typeId, Long.valueOf(userDTOId));
        Long cartId = Long.parseLong(cartDTO.get().getId());

        List<CartItemDTO> cartItemDTO = cartItemService.getAllCartItemDTOByCartId(cartId);

        result.put("cartDTO", cartDTO.get());
        result.put("cartItemDTO", cartItemDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> removeCartItem(@Validated @PathVariable Long id) {
        Optional<CartItemDTO> cartItemDTO = cartItemService.getCartItemDTOById(id);
        cartService.getCartDTOById(Long.valueOf(cartItemDTO.get().getCart().getId()));

        Cart newCart = cartService.reduceGrandTotalAndQuantityTotal(cartItemDTO.get());

        cartItemService.remove(id);

        return new ResponseEntity<>(newCart.toCartDTO(), HttpStatus.OK);
    }

    @PostMapping("/create/import")
    public ResponseEntity<?> doCreateImportCart(@Validated @RequestBody CartImportDTO cartImportDTO, BindingResult bindingResult) {

        new CartImportDTO().validate(cartImportDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        CartDTO cartDTO = new CartDTO();
        CartItemDTO cartItemDTO = new CartItemDTO();


        Optional<Product> productOptional = productService.findById(Long.valueOf(cartImportDTO.getProductId()));

        if (!productOptional.isPresent()) {
            throw new DataInputException("Product ID is not define");
        }

        cartItemDTO.setProduct(productOptional.get().toProductDTO());

        Optional<User> userOptional = userService.findById(Long.valueOf(cartImportDTO.getUserId()));

        if (!userOptional.isPresent()) {
            throw new DataInputException("User ID is not define");
        }

        cartDTO.setUser(userOptional.get().toUserDTO());

        Optional<Type> typeOptional = typeService.findById(Long.valueOf(cartImportDTO.getTypeId()));

        if (!typeOptional.isPresent()) {
            throw new DataInputException("Type ID is not define");
        }

        cartDTO.setType(typeOptional.get().toTypeDTO());

        Optional<Unit> unitOptional = unitService.findById(Long.valueOf(cartImportDTO.getUnitId()));

        if (!unitOptional.isPresent()) {
            throw new DataInputException("Unit ID is not define");
        }

        cartDTO.setUnit(unitOptional.get().toUnitDTO());

        Optional<Situation> situationOptional = situationService.findById(1L);

        cartDTO.setSituation(situationOptional.get().toSituationDTO());

        cartItemDTO.setQuantity(cartImportDTO.getQuantity());
        cartItemDTO.setPrice(cartImportDTO.getPrice());

        Map<String, Object> result = cartService.saveCartImportDTO(cartDTO, cartItemDTO);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }



    @PostMapping("/create/purchase")
    public ResponseEntity<?> doCreatePurchaseCart(@Validated @RequestBody CartPurchaseDTO cartPurchaseDTO, BindingResult bindingResult) {
        new CartPurchaseDTO().validate(cartPurchaseDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        CartDTO cartDTO = new CartDTO();
        CartItemDTO cartItemDTO = new CartItemDTO();


        Optional<Product> productOptional = productService.findById(Long.valueOf(cartPurchaseDTO.getProductId()));

        if (!productOptional.isPresent()) {
            throw new DataInputException("Product ID is not define");
        }

        cartItemDTO.setProduct(productOptional.get().toProductDTO());

        Optional<User> userOptional = userService.findById(Long.valueOf(cartPurchaseDTO.getUserId()));

        if (!userOptional.isPresent()) {
            throw new DataInputException("User ID is not define");
        }

        cartDTO.setUser(userOptional.get().toUserDTO());

        Optional<Type> typeOptional = typeService.findById(Long.valueOf(cartPurchaseDTO.getTypeId()));

        if (!typeOptional.isPresent()) {
            throw new DataInputException("Type ID is not define");
        }

        cartDTO.setType(typeOptional.get().toTypeDTO());

        Optional<Unit> unitOptional = unitService.findById(Long.valueOf(cartPurchaseDTO.getUnitId()));

        if (!unitOptional.isPresent()) {
            throw new DataInputException("Unit ID is not define");
        }

        cartDTO.setUnit(unitOptional.get().toUnitDTO());

        Optional<Situation> situationOptional = situationService.findById(1L);

        cartDTO.setSituation(situationOptional.get().toSituationDTO());

        cartItemDTO.setQuantity(cartPurchaseDTO.getQuantity());

        //Tao Cart trong service

        Map<String, Object> result = cartService.saveCartPurchaseDTO(cartDTO, cartItemDTO);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
