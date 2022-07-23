package com.chan.customer.domain;

import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @EqualsAndHashCode(of = "id")
public class Customer {

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    @NotNull
    @Column(name = "account_id", unique = true, length = 250)
    private String accountId;

    @NotNull
    private String name;

    @NotNull
    private String telephone;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Order> orderList = new ArrayList<>();

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
