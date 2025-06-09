package restaurantapi.entity;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuItemKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderMenuItemKey)) return false;
        OrderMenuItemKey that = (OrderMenuItemKey) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(menuItemId, that.menuItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, menuItemId);
    }
}
