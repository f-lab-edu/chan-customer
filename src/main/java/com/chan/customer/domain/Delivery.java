package com.chan.customer.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id @GeneratedValue
    @Column( name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
