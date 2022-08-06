package com.mvpt.service.order;

import com.mvpt.exception.DataInputException;
import com.mvpt.model.Cart;
import com.mvpt.model.CartItem;
import com.mvpt.model.Order;
import com.mvpt.model.Product;
import com.mvpt.model.dto.*;
import com.mvpt.repository.*;
import com.mvpt.service.cart.CartService;
import com.mvpt.service.cartItem.CartItemService;
import com.mvpt.service.orderItem.OrderItemService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.server.authentication.AnonymousAuthenticationWebFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public Optional<OrderDTO> getOrderDTOById(Long orderId) {
        return orderRepository.getOrderDTOById(orderId);
    }

    @Override
    public List<OrderDTO> getAllOrderDTOByTypeId(Long typeId) {
        return orderRepository.getAllOrderDTOByTypeId(typeId);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void saveOrderDTO(CartDTO cartDTO) {

        Long cartId = Long.valueOf(cartDTO.getId());

        //Tao order
        OrderDTO newOrderDTO = cartDTO.toOrderDTO();

            Order currentNewOrder = orderRepository.save(newOrderDTO.toOrder());
        OrderDTO currentNewOrderDTO = currentNewOrder.toOrderDTO();

        Long orderId = currentNewOrder.getId();

        //Lay list CartItem
        List<CartItemDTO> cartItemDTOList = cartItemRepository.getAllCartItemDTOByCartId(cartId);

        //Add vao OrderItem

        for (CartItemDTO cartItemDTO : cartItemDTOList){
            //Phai set rieng Order tu currentNewOrder
            OrderItemDTO newOrderItemDTO = cartItemDTO.toOrderItemDTO();
            newOrderItemDTO.setOrder(currentNewOrderDTO);

            orderItemRepository.save(newOrderItemDTO.toOrderItem());

            //Product: tang price, quantity, available & imported = true(1)

            Optional<ProductDTO> currentProductDTO = productRepository.getProductDTOById(Long.valueOf(newOrderItemDTO.getProduct().getId()));

            int quantityPrd = Integer.parseInt(currentProductDTO.get().getQuantity());
            int availablePrd = Integer.parseInt(currentProductDTO.get().getAvailable());

            BigDecimal priceOrderItem = new BigDecimal(newOrderItemDTO.getPrice());
            int quantityOrderItem = Integer.parseInt(newOrderItemDTO.getQuantity());

            currentProductDTO.get().setPrice(String.valueOf(priceOrderItem));
            currentProductDTO.get().setQuantity(String.valueOf(quantityPrd + quantityOrderItem));
            currentProductDTO.get().setAvailable(String.valueOf(availablePrd + quantityOrderItem));
            currentProductDTO.get().setImported(true);

            productRepository.save(currentProductDTO.get().toProduct());

            //Xoa CartItem
            cartItemRepository.deleteById(Long.valueOf(cartItemDTO.getId()));
        }

        //Xoa Cart by Id & CartItem By CartId
        cartRepository.deleteById(cartId);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }
}
