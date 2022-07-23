package com.chan.customer.repository;

import com.chan.customer.domain.Customer;
import com.chan.customer.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerAndOrderDateTimeBetween(Customer customer, LocalDateTime start, LocalDateTime end);
}
