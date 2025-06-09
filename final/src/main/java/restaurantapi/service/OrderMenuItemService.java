package restaurantapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurantapi.entity.MenuItem;
import restaurantapi.entity.Order;
import restaurantapi.entity.OrderMenuItem;
import restaurantapi.entity.OrderMenuItemKey;
import restaurantapi.repository.MenuItemRepository;
import restaurantapi.repository.OrderMenuItemRepository;
import restaurantapi.repository.OrderRepository;

@Service
public class OrderMenuItemService {

    @Autowired
    private OrderMenuItemRepository orderMenuItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<OrderMenuItem> getAll() {
        return orderMenuItemRepository.findAll();
    }

    public OrderMenuItem getById(OrderMenuItemKey id) {
        return orderMenuItemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("OrderMenuItem not found"));
    }

    public OrderMenuItem create(Long orderId, Long menuItemId, int quantity) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
            .orElseThrow(() -> new RuntimeException("Menu item not found"));

        OrderMenuItemKey key = new OrderMenuItemKey();
        key.setOrderId(orderId);
        key.setMenuItemId(menuItemId);

        OrderMenuItem omi = new OrderMenuItem();
        omi.setId(key);
        omi.setOrder(order);
        omi.setMenuItem(menuItem);
        omi.setQuantity(quantity);

        return orderMenuItemRepository.save(omi);
    }

    public OrderMenuItem updateQuantity(Long orderId, Long menuItemId, int quantity) {
        OrderMenuItemKey key = new OrderMenuItemKey();
        key.setOrderId(orderId);
        key.setMenuItemId(menuItemId);

        OrderMenuItem omi = getById(key);
        omi.setQuantity(quantity);
        return orderMenuItemRepository.save(omi);
    }

    public void delete(Long orderId, Long menuItemId) {
        OrderMenuItemKey key = new OrderMenuItemKey();
        key.setOrderId(orderId);
        key.setMenuItemId(menuItemId);
        orderMenuItemRepository.deleteById(key);
    }
}
