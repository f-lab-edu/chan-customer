package com.chan.customer.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderMenu> orderMenuList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDateTime;

    private LocalDate startDate;

    private LocalDate endDate;

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
        customer.getOrderList().add(this);
    }

    public void addOrderMenu(OrderMenu orderMenu){
        orderMenuList.add(orderMenu);
        orderMenu.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public static Order request(Customer customer, Delivery delivery, List<OrderMenu> orderMenuList){
        Order order = new Order();
        order.setCustomer(customer);
        order.setDelivery(delivery);

        for(var orderMenu : orderMenuList){
            order.addOrderMenu(orderMenu);
        }

        order.setStatus(OrderStatus.ORDER);
        order.setOrderDateTime(LocalDateTime.now());

        return order;
    }

    public int getTotalCount(){
        int total = 0;
        for(var menuItem : getOrderMenuList()){
            total += menuItem.getMenu().getMenuCount();
        }
        return total;
    }

    public int getTotalPrice(){
        int total = 0;
        for(var menuItem : getOrderMenuList()){
            total += menuItem.getTotalPrice();
        }
        return total;
    }

}
