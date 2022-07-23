package com.chan.customer.repository;

import com.chan.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByAccountId(String accountId);

}
