package restaurantapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restaurantapi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
