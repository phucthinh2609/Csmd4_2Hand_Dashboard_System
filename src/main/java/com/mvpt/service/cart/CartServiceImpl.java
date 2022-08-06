package com.mvpt.service.cart;

import com.mvpt.exception.DataInputException;
import com.mvpt.model.Cart;
import com.mvpt.model.dto.*;
import com.mvpt.repository.CartItemRepository;
import com.mvpt.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static javax.swing.text.html.HTML.Tag.OL;

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
    public Map<String, Object> saveCartImportDTO(CartDTO cartDTO, CartItemDTO cartItemDTO) {
        ProductDTO productDTO = cartItemDTO.getProduct();
        UserDTO userDTO = cartDTO.getUser();
        TypeDTO typeDTO = cartDTO.getType();
        SituationDTO situationDTO = cartDTO.getSituation();
        UnitDTO unitDTO = cartDTO.getUnit();

        Long userId = Long.valueOf(userDTO.getId());
        Long productId = Long.valueOf(productDTO.getId());
        Long typeId = Long.valueOf(typeDTO.getId());

        Optional<CartDTO> currentCartDTO = cartRepository.getCartDTOByTypeIdAndUserId(typeId, userId);
        Optional<CartItemDTO> currentCartItemDTO = cartItemRepository.getCartItemDTOByProductId(productId);


        BigDecimal price = new BigDecimal(cartItemDTO.getPrice());
        int quantity = Integer.parseInt(cartItemDTO.getQuantity());
        String totalPrice = String.valueOf(price.multiply(BigDecimal.valueOf(quantity)));

        CartDTO newCartDTO;
        CartItemDTO newCartItemDTO;

        Map<String, Object> result = new HashMap<>();

        if (!currentCartDTO.isPresent()) {
            //Tao moi Cart
            newCartDTO = new CartDTO("0", totalPrice, String.valueOf(quantity), userDTO, typeDTO, situationDTO, unitDTO);

            try {
                CartDTO currentNewCartDTO = cartRepository.save(newCartDTO.toCart()).toCartDTO();

                String successFirst = "Add a new cart successful";
                result.put("successCartCre", successFirst);

                //Tao moi CartItem
                newCartItemDTO = new CartItemDTO("0", String.valueOf(price), String.valueOf(quantity), totalPrice, currentNewCartDTO, productDTO);

                try {
                    cartItemRepository.save(newCartItemDTO.toCartItem());

                    String success = "Add a new cart item successful";
                    result.put("success", success);

                }catch (Exception ex) {
                    throw new DataInputException("Please contact to management");
                }
                return result;

            }catch (Exception ex) {
                throw new DataInputException("Please contact to management");
            }

        } else {
            if (!currentCartItemDTO.isPresent()) {
                //Tao moi CartItem
                newCartItemDTO = new CartItemDTO("0", String.valueOf(price), String.valueOf(quantity), totalPrice, currentCartDTO.get(), productDTO);

                try {
                    cartItemRepository.save(newCartItemDTO.toCartItem()).toCartItemDTO();
                    String success = "Add a new cart item successful";
                    result.put("success", success);

                    //Cap nhat grandTotal, grandQuantity

                    BigDecimal grandTotalUp = new BigDecimal(Long.parseLong(currentCartDTO.get().getGrandTotal()));
                    int quantityUp = Integer.parseInt(currentCartDTO.get().getQuantityTotal());

                    currentCartDTO.get().setGrandTotal(String.valueOf(grandTotalUp.add(new BigDecimal(Long.parseLong(totalPrice)))));
                    currentCartDTO.get().setQuantityTotal(String.valueOf(quantityUp + quantity));

                    cartRepository.save(currentCartDTO.get().toCart());

                }catch (Exception ex) {
                    throw new DataInputException("Please contact to management");
                }

                return result;

            }else {
                //Reduce grandTotal, quantityTotal cua currentCartDTO  - currentCartItemDTO cu
                reduceGrandTotalAndQuantityTotal(currentCartItemDTO.get());

                //Cap nhat lai price, quantity, total price Cart Item
                currentCartItemDTO.get().setPrice(String.valueOf(price));
                currentCartItemDTO.get().setQuantity(String.valueOf(quantity));
                currentCartItemDTO.get().setTotalPrice(String.valueOf(new BigDecimal(Long.parseLong(totalPrice))));

                cartItemRepository.save(currentCartItemDTO.get().toCartItem());
                String success = "Update cart item successful";
                result.put("success", success);

                //Increment grandTotal, quantityTotal cua currentCartDTO - newCartItemDTO moi
                incrementGrandTotalAndQuantityTotal(currentCartItemDTO.get());

                return result;
            }
        }
    }

    @Override
    public Map<String, Object> saveCartPurchaseDTO(CartDTO cartDTO, CartItemDTO cartItemDTO) {

        ProductDTO productDTO = cartItemDTO.getProduct();
        UserDTO userDTO = cartDTO.getUser();
        TypeDTO typeDTO = cartDTO.getType();
        SituationDTO situationDTO = cartDTO.getSituation();
        UnitDTO unitDTO = cartDTO.getUnit();

        Long userId = Long.valueOf(userDTO.getId());
        Long productId = Long.valueOf(productDTO.getId());
        Long typeId = Long.valueOf(typeDTO.getId());

        Optional<CartDTO> currentCartDTO = cartRepository.getCartDTOByTypeIdAndUserId(typeId, userId);
        Optional<CartItemDTO> currentCartItemDTO = cartItemRepository.getCartItemDTOByProductId(productId);


        BigDecimal price = new BigDecimal(productDTO.getPrice());
        int quantity = Integer.parseInt(cartItemDTO.getQuantity());
        String totalPrice = String.valueOf(price.multiply(BigDecimal.valueOf(quantity)));

        //Bat Quantity
        if (quantity > Long.parseLong(productDTO.getQuantity())) {
            throw new DataInputException("The number of items has been exceeded");
        }

        CartDTO newCartDTO;
        CartItemDTO newCartItemDTO;

        Map<String, Object> result = new HashMap<>();

        if (!currentCartDTO.isPresent()) {
            //Tao moi Cart
            newCartDTO = new CartDTO("0", totalPrice, String.valueOf(quantity), userDTO, typeDTO, situationDTO, unitDTO);

            try {
                CartDTO currentNewCartDTO = cartRepository.save(newCartDTO.toCart()).toCartDTO();

                String successFirst = "Add a new cart successful";
                result.put("successCartCre", successFirst);

                //Tao moi CartItem
                newCartItemDTO = new CartItemDTO("0", String.valueOf(price), String.valueOf(quantity), totalPrice, currentNewCartDTO, productDTO);

                try {
                    cartItemRepository.save(newCartItemDTO.toCartItem());

                    String success = "Add a new cart item successful";
                    result.put("success", success);

                }catch (Exception ex) {
                    throw new DataInputException("Please contact to management");
                }
                return result;

            }catch (Exception ex) {
                throw new DataInputException("Please contact to management");
            }

        } else {
            if (!currentCartItemDTO.isPresent()) {
                //Tao moi CartItem && Cap nhat grandTotal, grandQuantity
                newCartItemDTO = new CartItemDTO("0", String.valueOf(price), String.valueOf(quantity), totalPrice, currentCartDTO.get(), productDTO);

                try {
                    cartItemRepository.save(newCartItemDTO.toCartItem()).toCartItemDTO();
                    String success = "Add a new cart item successful";
                    result.put("success", success);

                    BigDecimal grandTotalUp = new BigDecimal(Long.parseLong(currentCartDTO.get().getGrandTotal()));
                    int quantityUp = Integer.parseInt(currentCartDTO.get().getQuantityTotal());

                    currentCartDTO.get().setGrandTotal(String.valueOf(grandTotalUp.add(new BigDecimal(Long.parseLong(totalPrice)))));
                    currentCartDTO.get().setQuantityTotal(String.valueOf(quantityUp + quantity));

                    cartRepository.save(currentCartDTO.get().toCart());

                }catch (Exception ex) {
                    throw new DataInputException("Please contact to management");
                }

                return result;

            }else {
                //Reduce grandTotal, quantityTotal cua currentCartDTO  - currentCartItemDTO cu
                reduceGrandTotalAndQuantityTotal(currentCartItemDTO.get());

                //Cap nhat lai quantity, total price Cart Item
                currentCartItemDTO.get().setQuantity(String.valueOf(quantity));
                currentCartItemDTO.get().setTotalPrice(String.valueOf(new BigDecimal(Long.parseLong(totalPrice))));

                cartItemRepository.save(currentCartItemDTO.get().toCartItem());
                String success = "Update cart item successful";
                result.put("success", success);

                //Increment grandTotal, quantityTotal cua currentCartDTO - newCartItemDTO moi
                incrementGrandTotalAndQuantityTotal(currentCartItemDTO.get());

                return result;
            }
        }

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
