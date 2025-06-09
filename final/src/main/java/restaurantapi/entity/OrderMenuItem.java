package restaurantapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuItem {

    @EmbeddedId
    private OrderMenuItemKey id = new OrderMenuItemKey();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "order-orderMenuItems")
    private Order order;

    @ManyToOne
    @MapsId("menuItemId")
    @JoinColumn(name = "menu_item_id")
    @JsonBackReference(value = "menuItem-orderMenuItems")
    private MenuItem menuItem;


    private int quantity;
}
