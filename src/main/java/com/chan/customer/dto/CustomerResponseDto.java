package com.chan.customer.dto;

import com.chan.customer.domain.Address;
import com.chan.customer.domain.Customer;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CustomerResponseDto {

    private String name;

    private String telephone;

    private Address address;

    public CustomerResponseDto(Customer customer) {
        this.name = customer.getName();
        this.telephone = customer.getTelephone();
        this.address = customer.getAddress();
    }
}
