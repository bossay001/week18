package restaurantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurantapi.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
