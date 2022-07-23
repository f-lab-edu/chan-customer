package com.chan.customer.domain;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class OrderMenu {

    @Id @GeneratedValue
    @Column(name = "order_menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Menu menu;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        menu.setOrderMenu(this);
    }

    public int getTotalPrice() {
        return menu.getMenuPrice() * menu.getMenuCount();
    }
}
