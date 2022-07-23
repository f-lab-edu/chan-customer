package com.chan.customer.service;

import com.chan.customer.domain.Address;
import com.chan.customer.domain.Customer;
import com.chan.customer.exception.CustomerFindFailedException;
import com.chan.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void customerSignUp() throws Exception{

        Customer customer = new Customer();
        customer.setAccountId("test");
        customer.setName("woong");
        customer.setAddress(new Address("서울시 종로구", 11110));

        Customer customer1 = customerService.signUp(customer);
        Customer findCustomer = customerRepository.findById(customer1.getId()).orElseThrow();

        Assertions.assertEquals(customer, findCustomer);
    }

    @Test
    public void customerUpdate() throws Exception{

        Customer customer = new Customer();
        customer.setAccountId("update");
        customer.setName("woong");
        customer.setAddress(new Address("서울시 종로구", 11110));

        Customer customer1 = customerService.signUp(customer);

        Customer findCustomer = customerRepository.findById(customer1.getId()).orElseThrow();
        findCustomer.setAddress(new Address("서울시 중구", 11140));

        customerService.update(findCustomer);

        Customer changeCustomer = customerRepository.findById(customer1.getId()).orElseThrow();

        Assertions.assertEquals(changeCustomer.getAddress().getSigunguCode(), 11140);
    }

    @Test
    public void customerOverlap() throws  Exception{

        Address address = new Address();
        Customer customer1 = new Customer();
        customer1.setAccountId("test1");
        customer1.setName("ww");
        customer1.setTelephone("01012345678");
        customer1.setAddress(address);

        Customer customer2 = new Customer();
        customer2.setAccountId("test1");
        customer2.setName("ww");
        customer2.setTelephone("01012345678");
        customer2.setAddress(address);

        customerService.signUp(customer1);
        Assertions.assertThrows(CustomerFindFailedException.class, () -> {
            customerService.signUp(customer2);
        });

    }
}