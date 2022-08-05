CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_create_order`(
	IN typeIdCart INT,
    IN userIdCart INT
)
BEGIN

    DECLARE cartId INT;
    DECLARE orderId INT;

    SELECT c.id INTO cartId
    FROM carts AS c
    WHERE c.type_id = typeIdCart
      AND c.user_id = userIdCart;

    INSERT INTO orders(created_at, created_by, updated_at, updated_by, deleted, grand_total, quantity_total, situation_id, type_id, unit_id, user_id)
    SELECT NOW(), c.created_by, NOW(), c.updated_by, c.deleted, c.grand_total, c.quantity_total, c.situation_id, c.type_id, c.unit_id, c.user_id
    FROM carts AS c
    WHERE c.id = cartId;

    SET orderId = (SELECT MAX(id) FROM orders);

    INSERT INTO order_items(created_at, created_by, updated_at, updated_by, deleted, price, quantity, total_price, order_id)
    SELECT NOW(), ci.created_by, NOW(), ci.updated_by, ci.deleted, ci.price, ci.quantity, ci.total_price, orderId
    FROM cart_items ci
    WHERE ci.cart_id = cartId;

    DELETE FROM cart_items AS ci
    WHERE ci.cart_id = cartId;

    DELETE FROM carts AS c
    WHERE c.id = cartId;

END