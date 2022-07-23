package com.chan.customer.dto;

import com.chan.customer.domain.Address;
import com.chan.customer.domain.Customer;
import com.sun.istack.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
public class CustomerDto {

    @NotNull
    private String accountId;

    @NotNull
    private String name;

    @NotNull
    private String telephone;

    private Address address;

    public Customer toEntity(){
        Customer customer = new Customer();
        customer.setAccountId(accountId);
        customer.setAddress(address);
        customer.setName(name);
        customer.setTelephone(telephone);
        return customer;
    }
}
