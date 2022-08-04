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
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
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

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody CartImportDTO cartImportDTO, BindingResult bindingResult) {

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

        Optional<Situation> situationOptional = situationService.findById(1L);
        Optional<Unit> unitOptional = unitService.findById(1L);

        ProductDTO currentProductDTO = productOptional.get().toProductDTO();
        UserDTO currentUserDTO = userOptional.get().toUserDTO();
        TypeDTO currentTypeDTO = typeOptional.get().toTypeDTO();
        SituationDTO currentSituationDTO = situationOptional.get().toSituationDTO();
        UnitDTO currentUnitDTO = unitOptional.get().toUnitDTO();

        Long userId = userOptional.get().getId();
        Long productId = productOptional.get().getId();

        Optional<CartDTO> currentCartDTO = cartService.getCartDTOByUserId(userId);
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
            newCartDTO.setGrandTotal(String.valueOf(price));
            newCartDTO.setQuantityTotal(String.valueOf(1));
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
                    CartItemDTO currentNewCartItemDTO = cartItemService.save(newCartItemDTO.toCartItem()).toCartItemDTO();
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
                //Cap nhat tang CartItem && Cap nhat grandTotal, grandQuantity
                BigDecimal currentCartItemPrice = new BigDecimal(Long.parseLong(currentCartItemDTO.get().getPrice()));
                int currentCartItemQuantity = Integer.parseInt(currentCartItemDTO.get().getQuantity());
                BigDecimal currentCartItemTotalPrice = new BigDecimal(Long.parseLong(currentCartItemDTO.get().getTotalPrice()));

                currentCartItemDTO.get().setPrice(String.valueOf(price));
                currentCartItemDTO.get().setQuantity(String.valueOf(currentCartItemQuantity + quantity));
                currentCartItemDTO.get().setTotalPrice(String.valueOf(currentCartItemTotalPrice.add(new BigDecimal(Long.parseLong(totalPrice)))));

                try {
                    cartItemService.save(currentCartItemDTO.get().toCartItem());
                    String success = "Update cart item successful";
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
            }
        }


    }
}
