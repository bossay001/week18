package restaurantapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restaurantapi.entity.OrderMenuItem;
import restaurantapi.entity.OrderMenuItemKey;
import restaurantapi.service.OrderMenuItemService;

@RestController
@RequestMapping("/api/order-menu-items")
public class OrderMenuItemController {

    @Autowired
    private OrderMenuItemService orderMenuItemService;

    @GetMapping
    public List<OrderMenuItem> getAll() {
        return orderMenuItemService.getAll();
    }

    @GetMapping("/order/{orderId}/menu-item/{menuItemId}")
    public OrderMenuItem getById(@PathVariable Long orderId, @PathVariable Long menuItemId) {
        OrderMenuItemKey key = new OrderMenuItemKey();
        key.setOrderId(orderId);
        key.setMenuItemId(menuItemId);
        return orderMenuItemService.getById(key);
    }

    @PostMapping("/order/{orderId}/menu-item/{menuItemId}")
    public OrderMenuItem create(
        @PathVariable Long orderId,
        @PathVariable Long menuItemId,
        @RequestParam int quantity
    ) {
        return orderMenuItemService.create(orderId, menuItemId, quantity);
    }

    @PutMapping("/order/{orderId}/menu-item/{menuItemId}")
    public OrderMenuItem updateQuantity(
        @PathVariable Long orderId,
        @PathVariable Long menuItemId,
        @RequestParam int quantity
    ) {
        return orderMenuItemService.updateQuantity(orderId, menuItemId, quantity);
    }

    @DeleteMapping("/order/{orderId}/menu-item/{menuItemId}")
    public void delete(@PathVariable Long orderId, @PathVariable Long menuItemId) {
        orderMenuItemService.delete(orderId, menuItemId);
    }
}
