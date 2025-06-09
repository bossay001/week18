package restaurantapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurantapi.entity.Customer;
import restaurantapi.entity.MenuItem;
import restaurantapi.entity.Order;
import restaurantapi.entity.OrderMenuItem;
import restaurantapi.repository.CustomerRepository;
import restaurantapi.repository.MenuItemRepository;
import restaurantapi.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order createOrder(Long customerId, Order order) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        order.setCustomer(customer);

        if (order.getOrderMenuItems() != null) {
            for (OrderMenuItem omi : order.getOrderMenuItems()) {
                // Load the managed MenuItem from the database
                Long menuItemId = omi.getMenuItem().getMenuItemId();
                MenuItem managedMenuItem = menuItemRepository.findById(menuItemId)
                    .orElseThrow(() -> new RuntimeException("MenuItem with ID " + menuItemId + " not found"));

                omi.setOrder(order); // set the back-reference to Order
                omi.setMenuItem(managedMenuItem); // set managed MenuItem entity

                // Set the composite key IDs
                omi.getId().setOrderId(order.getOrderId()); // This might be null at this point (before save)
                omi.getId().setMenuItemId(managedMenuItem.getMenuItemId());
            }
        }

        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order order = getOrderById(id);
        order.setOrderDate(updatedOrder.getOrderDate());
        order.setStatus(updatedOrder.getStatus());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
