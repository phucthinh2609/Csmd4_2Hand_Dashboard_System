package com.mvpt.controller.rest;

import com.mvpt.exception.DataInputException;
import com.mvpt.model.dto.CartDTO;
import com.mvpt.model.dto.CartItemDTO;
import com.mvpt.model.dto.OrderDTO;
import com.mvpt.model.dto.UserDTO;
import com.mvpt.service.cart.CartService;
import com.mvpt.service.cartItem.CartItemService;
import com.mvpt.service.order.OrderService;
import com.mvpt.service.product.ProductService;
import com.mvpt.service.user.UserService;
import com.mvpt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    ProductService productService;

    @Autowired
    AppUtils appUtils;

    @GetMapping("/imports")
    public ResponseEntity<?> getAllByImportType() {
        List<OrderDTO> orderDTOList = orderService.getAllOrderDTOByTypeId(1L);
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    @GetMapping("/purchases")
    public ResponseEntity<?> getAllByPurchaseType() {
        List<OrderDTO> orderDTOList = orderService.getAllOrderDTOByTypeId(2L);
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    @PostMapping("/create/import")
    public ResponseEntity<?> doImportOrder(@Validated @RequestBody CartDTO cartDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<CartDTO> currentCartDTO = cartService.getCartDTOById(Long.valueOf(cartDTO.getId()));

        if (!currentCartDTO.isPresent()) {
            throw new DataInputException("Cart info is not define!!!");
        }

        Optional<UserDTO> currentUserDTO = userService.getUserDTOById(Long.parseLong(cartDTO.getUser().getId()));

        if (!currentUserDTO.isPresent()) {
            throw new DataInputException("User info is not define!!!");
        }

        cartDTO.setUser(currentUserDTO.get());

        List<CartItemDTO> cartItemDTOList = cartItemService.getAllCartItemDTOByCartId(cartDTO.toCart().getId());

        if (cartItemDTOList.isEmpty()) {
            throw new DataInputException("Cart is empty");
        }

        orderService.saveImportOrderDTO(cartDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/purchase")
    public ResponseEntity<?> doPurchaseOrder(@Validated @RequestBody CartDTO cartDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<CartDTO> currentCartDTO = cartService.getCartDTOById(Long.valueOf(cartDTO.getId()));

        if (!currentCartDTO.isPresent()) {
            throw new DataInputException("Cart info is not define!!!");
        }

        Optional<UserDTO> currentUserDTO = userService.getUserDTOById(Long.parseLong(cartDTO.getUser().getId()));

        if (!currentUserDTO.isPresent()) {
            throw new DataInputException("User info is not define!!!");
        }

        cartDTO.setUser(currentUserDTO.get());

        List<CartItemDTO> cartItemDTOList = cartItemService.getAllCartItemDTOByCartId(cartDTO.toCart().getId());

        if (cartItemDTOList.isEmpty()) {
            throw new DataInputException("Cart is empty");
        }

        orderService.savePurchaseOrderDTO(cartDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
