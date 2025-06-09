package restaurantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurantapi.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {}
