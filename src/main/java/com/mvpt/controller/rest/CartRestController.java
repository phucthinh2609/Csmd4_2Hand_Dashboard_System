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

    @GetMapping("/cart-items/import")
    public ResponseEntity<?> getAllCartItemImport() {

        Map<String, Object> result = new HashMap<>();

        Long typeId = 1L;

        Optional<CartDTO> cartDTO = cartService.getCartDTOByTypeId(typeId);
        Long cartId = Long.parseLong(cartDTO.get().getId());

        List<CartItemDTO> cartItemDTO = cartItemService.getAllCartItemDTOByCartId(cartId);

        result.put("cartDTO", cartDTO.get());
        result.put("cartItemDTO", cartItemDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/cart-items/purchase")
    public ResponseEntity<?> getAllCartItemPurchase() {

        Map<String, Object> result = new HashMap<>();

        Long typeId = 2L;

        Optional<CartDTO> cartDTO = cartService.getCartDTOByTypeId(typeId);
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

        Optional<Product> productOptional = productService.findById(Long.valueOf(cartImportDTO.getProductId()));

        if (!productOptional.isPresent()) {
            throw new DataInputException("Product ID is not define");
        }

        Optional<User> userOptional = userService.findById(Long.valueOf(cartImportDTO.getUserId()));

        if (!userOptional.isPresent()) {
            throw new DataInputException("User ID is not define");
        }

        Optional<Type> typeOptional = typeService.findById(Long.valueOf(cartImportDTO.getTypeId()));

        if (!typeOptional.isPresent()) {
            throw new DataInputException("Type ID is not define");
        }

        Optional<Unit> unitOptional = unitService.findById(Long.valueOf(cartImportDTO.getUnitId()));

        if (!unitOptional.isPresent()) {
            throw new DataInputException("Unit ID is not define");
        }

        Optional<Situation> situationOptional = situationService.findById(1L);

        ProductDTO currentProductDTO = productOptional.get().toProductDTO();
        UserDTO currentUserDTO = userOptional.get().toUserDTO();
        TypeDTO currentTypeDTO = typeOptional.get().toTypeDTO();
        SituationDTO currentSituationDTO = situationOptional.get().toSituationDTO();
        UnitDTO currentUnitDTO = unitOptional.get().toUnitDTO();

        Long userId = userOptional.get().getId();
        Long productId = productOptional.get().getId();
        Long typeId = typeOptional.get().getId();

        Optional<CartDTO> currentCartDTO = cartService.getCartDTOByTypeIdAndUserId(typeId, userId);
        Optional<CartItemDTO> currentCartItemDTO = cartItemService.getCartItemDTOByProductId(productId);

        BigDecimal price = new BigDecimal(cartImportDTO.getPrice());
        int quantity = Integer.parseInt(cartImportDTO.getQuantity());
        String totalPrice = String.valueOf(price.multiply(BigDecimal.valueOf(quantity)));

        CartDTO newCartDTO = new CartDTO();
        CartItemDTO newCartItemDTO = new CartItemDTO();

        Map<String, Object> result = new HashMap<>();

        if (!currentCartDTO.isPresent()) {
            //Tao moi Cart & CartItem
            newCartDTO.setId(String.valueOf(0L));
            newCartDTO.setGrandTotal(String.valueOf(totalPrice));
            newCartDTO.setQuantityTotal(String.valueOf(quantity));
            newCartDTO.setUser(currentUserDTO);
            newCartDTO.setType(currentTypeDTO);
            newCartDTO.setSituation(currentSituationDTO);
            newCartDTO.setUnit(currentUnitDTO);

            try {
                CartDTO currentNewCartDTO = cartService.save(newCartDTO.toCart()).toCartDTO();

                String successFirst = "Add a new cart successful";
                result.put("successCartCre", successFirst);

                newCartItemDTO.setId(String.valueOf(0L));
                newCartItemDTO.setPrice(String.valueOf(price));
                newCartItemDTO.setQuantity(String.valueOf(quantity));
                newCartItemDTO.setTotalPrice(totalPrice);
                newCartItemDTO.setCart(currentNewCartDTO);
                newCartItemDTO.setProduct(currentProductDTO);

                try {
                    cartItemService.save(newCartItemDTO.toCartItem());

                    String success = "Add a new cart item successful";
                    result.put("success", success);

                }catch (Exception ex) {
                    throw new DataInputException("Please contact to management");
                }

                return new ResponseEntity<>(result, HttpStatus.OK);

            }catch (Exception ex) {
                throw new DataInputException("Please contact to management");
            }

        } else {
            if (!currentCartItemDTO.isPresent()) {
                //Tao moi CartItem && Cap nhat grandTotal, grandQuantity
                newCartItemDTO.setId(String.valueOf(0L));
                newCartItemDTO.setPrice(String.valueOf(price));
                newCartItemDTO.setQuantity(String.valueOf(quantity));
                newCartItemDTO.setTotalPrice(totalPrice);
                newCartItemDTO.setCart(currentCartDTO.get());
                newCartItemDTO.setProduct(currentProductDTO);

                try {
                    cartItemService.save(newCartItemDTO.toCartItem()).toCartItemDTO();
                    String success = "Add a new cart item successful";
                    result.put("success", success);

                    BigDecimal grandTotalUp = new BigDecimal(Long.parseLong(currentCartDTO.get().getGrandTotal()));
                    int quantityUp = Integer.parseInt(currentCartDTO.get().getQuantityTotal());

                    currentCartDTO.get().setGrandTotal(String.valueOf(grandTotalUp.add(new BigDecimal(Long.parseLong(totalPrice)))));
                    currentCartDTO.get().setQuantityTotal(String.valueOf(quantityUp + quantity));

                    cartService.save(currentCartDTO.get().toCart());

                }catch (Exception ex) {
                    throw new DataInputException("Please contact to management");
                }

                return new ResponseEntity<>(result, HttpStatus.OK);

            }else {
                //Reduce grandTotal, quantityTotal cua currentCartDTO  - currentCartItemDTO cu
                cartService.reduceGrandTotalAndQuantityTotal(currentCartItemDTO.get());

                //Cap nhat lai price, quantity, total price Cart Item
                currentCartItemDTO.get().setPrice(String.valueOf(price));
                currentCartItemDTO.get().setQuantity(String.valueOf(quantity));
                currentCartItemDTO.get().setTotalPrice(String.valueOf(new BigDecimal(Long.parseLong(totalPrice))));

                cartItemService.save(currentCartItemDTO.get().toCartItem());
                String success = "Update cart item successful";
                result.put("success", success);

                //Increment grandTotal, quantityTotal cua currentCartDTO - newCartItemDTO moi
                cartService.incrementGrandTotalAndQuantityTotal(currentCartItemDTO.get());

                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }
    }



    @PostMapping("/create/purchase")
    public ResponseEntity<?> doCreatePurchaseCart(@Validated @RequestBody CartPurchaseDTO cartPurchaseDTO, BindingResult bindingResult) {
        new CartPurchaseDTO().validate(cartPurchaseDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<Product> productOptional = productService.findById(Long.valueOf(cartPurchaseDTO.getProductId()));

        if (!productOptional.isPresent()) {
            throw new DataInputException("Product ID is not define");
        }

        Optional<User> userOptional = userService.findById(Long.valueOf(cartPurchaseDTO.getUserId()));

        if (!userOptional.isPresent()) {
            throw new DataInputException("User ID is not define");
        }

        Optional<Type> typeOptional = typeService.findById(Long.valueOf(cartPurchaseDTO.getTypeId()));

        if (!typeOptional.isPresent()) {
            throw new DataInputException("Type ID is not define");
        }

        Optional<Unit> unitOptional = unitService.findById(Long.valueOf(cartPurchaseDTO.getUnitId()));

        if (!unitOptional.isPresent()) {
            throw new DataInputException("Unit ID is not define");
        }

        Optional<Situation> situationOptional = situationService.findById(1L);

        ProductDTO currentProductDTO = productOptional.get().toProductDTO();
        UserDTO currentUserDTO = userOptional.get().toUserDTO();
        TypeDTO currentTypeDTO = typeOptional.get().toTypeDTO();
        SituationDTO currentSituationDTO = situationOptional.get().toSituationDTO();
        UnitDTO currentUnitDTO = unitOptional.get().toUnitDTO();

        Long userId = userOptional.get().getId();
        Long productId = productOptional.get().getId();
        Long typeId = typeOptional.get().getId();

        Optional<CartDTO> currentCartDTO = cartService.getCartDTOByTypeIdAndUserId(typeId, userId);
        Optional<CartItemDTO> currentCartItemDTO = cartItemService.getCartItemDTOByProductId(productId);

        //Tach ham sang service

        BigDecimal price = new BigDecimal(currentProductDTO.getPrice());
        int quantity = Integer.parseInt(cartPurchaseDTO.getQuantity());
        String totalPrice = String.valueOf(price.multiply(BigDecimal.valueOf(quantity)));

        //Bat Quantity
        if (quantity > Long.parseLong(currentProductDTO.getQuantity())) {
            throw new DataInputException("The number of items has been exceeded");
        }

        CartDTO newCartDTO = new CartDTO();
        CartItemDTO newCartItemDTO = new CartItemDTO();

        Map<String, Object> result = new HashMap<>();

        if (!currentCartDTO.isPresent()) {
            //Tao moi Cart
            newCartDTO.setId(String.valueOf(0L));
            newCartDTO.setGrandTotal(String.valueOf(totalPrice));
            newCartDTO.setQuantityTotal(String.valueOf(quantity));
            newCartDTO.setUser(currentUserDTO);
            newCartDTO.setType(currentTypeDTO);
            newCartDTO.setSituation(currentSituationDTO);
            newCartDTO.setUnit(currentUnitDTO);

            try {
                CartDTO currentNewCartDTO = cartService.save(newCartDTO.toCart()).toCartDTO();

                String successFirst = "Add a new cart successful";
                result.put("successCartCre", successFirst);

                //Tao moi CartItem
                newCartItemDTO.setId(String.valueOf(0L));
                newCartItemDTO.setPrice(String.valueOf(price));
                newCartItemDTO.setQuantity(String.valueOf(quantity));
                newCartItemDTO.setTotalPrice(totalPrice);
                newCartItemDTO.setCart(currentNewCartDTO);
                newCartItemDTO.setProduct(currentProductDTO);

                try {
                    cartItemService.save(newCartItemDTO.toCartItem());

                    String success = "Add a new cart item successful";
                    result.put("success", success);

                }catch (Exception ex) {
                    throw new DataInputException("Please contact to management");
                }

                return new ResponseEntity<>(result, HttpStatus.OK);

            }catch (Exception ex) {
                throw new DataInputException("Please contact to management");
            }

        } else {
            if (!currentCartItemDTO.isPresent()) {
                //Tao moi CartItem && Cap nhat grandTotal, grandQuantity
                newCartItemDTO.setId(String.valueOf(0L));
                newCartItemDTO.setPrice(String.valueOf(price));
                newCartItemDTO.setQuantity(String.valueOf(quantity));
                newCartItemDTO.setTotalPrice(totalPrice);
                newCartItemDTO.setCart(currentCartDTO.get());
                newCartItemDTO.setProduct(currentProductDTO);

                try {
                    cartItemService.save(newCartItemDTO.toCartItem()).toCartItemDTO();
                    String success = "Add a new cart item successful";
                    result.put("success", success);

                    BigDecimal grandTotalUp = new BigDecimal(Long.parseLong(currentCartDTO.get().getGrandTotal()));
                    int quantityUp = Integer.parseInt(currentCartDTO.get().getQuantityTotal());

                    currentCartDTO.get().setGrandTotal(String.valueOf(grandTotalUp.add(new BigDecimal(Long.parseLong(totalPrice)))));
                    currentCartDTO.get().setQuantityTotal(String.valueOf(quantityUp + quantity));

                    cartService.save(currentCartDTO.get().toCart());

                }catch (Exception ex) {
                    throw new DataInputException("Please contact to management");
                }

                return new ResponseEntity<>(result, HttpStatus.OK);

            }else {
                //Reduce grandTotal, quantityTotal cua currentCartDTO  - currentCartItemDTO cu
                cartService.reduceGrandTotalAndQuantityTotal(currentCartItemDTO.get());

                //Cap nhat lai quantity, total price Cart Item
                currentCartItemDTO.get().setQuantity(String.valueOf(quantity));
                currentCartItemDTO.get().setTotalPrice(String.valueOf(new BigDecimal(Long.parseLong(totalPrice))));

                cartItemService.save(currentCartItemDTO.get().toCartItem());
                String success = "Update cart item successful";
                result.put("success", success);

                //Increment grandTotal, quantityTotal cua currentCartDTO - newCartItemDTO moi
                cartService.incrementGrandTotalAndQuantityTotal(currentCartItemDTO.get());

                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }
    }
}
