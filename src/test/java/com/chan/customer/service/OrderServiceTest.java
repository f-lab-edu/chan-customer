package com.chan.customer.service;

import com.chan.customer.DatabaseTest;
import com.chan.customer.domain.*;
import com.chan.customer.dto.MenuDto;
import com.chan.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OrderServiceTest extends DatabaseTest {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void makeDelivery(){

        Customer customer = signUp("test1");

        Delivery delivery = orderService.makeDelivery(customer);

        Assertions.assertEquals(customer.getAddress(), delivery.getAddress());

    }

    @Test
    public void saveOrder(){

        Customer customer = signUp("test22");

        Delivery delivery = orderService.makeDelivery(customer);

        MenuDto menuDto = new MenuDto();
        menuDto.setMenuNo(1L);
        menuDto.setMenuName("메뉴 이름");
        menuDto.setMenuPlan("1010100");
        menuDto.setMenuPrice(30000);
        menuDto.setMenuCount(1);

        Menu menu = menuDto.toEntity();

        Order order = orderService.saveOrder(customer, delivery, menu);

    }

    private Customer signUp(String accountId){

        Customer customer = new Customer();
        customer.setAccountId(accountId);
        customer.setName("woong");
        customer.setTelephone("010-1234-1234");
        customer.setAddress(new Address("서울시 종로구", 11110));

        return customerService.signUp(customer);
    }

}