package restaurantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurantapi.entity.OrderMenuItem;
import restaurantapi.entity.OrderMenuItemKey;

public interface OrderMenuItemRepository extends JpaRepository<OrderMenuItem, OrderMenuItemKey> {}
