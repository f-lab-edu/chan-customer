package com.chan.customer.dto;

import com.chan.customer.domain.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponseDto {

    private String accountId;

    private Long orderId;

    private String customerName;

    private List<MenuDto> menuList = new ArrayList<>();

    private Address deliveryAddress;

    private OrderStatus status;

    private LocalDateTime orderDateTime;

    private LocalDate startDate;

    private LocalDate endDate;

    public OrderResponseDto(Order order) {
        this.accountId = order.getCustomer().getAccountId();
        this.orderId = order.getId();
        this.customerName = order.getCustomer().getName();
        this.deliveryAddress = order.getDelivery().getAddress();
        this.status = order.getStatus();
        this.orderDateTime = order.getOrderDateTime();

        for(var m : order.getOrderMenuList()){
            MenuDto menuDto = new MenuDto();
            Menu menu = m.getMenu();
            menuDto.setMenuNo(menu.getMenuNo());
            menuDto.setMenuCount(menu.getMenuCount());
            menuDto.setMenuPlan(menu.getMenuPlan());
            menuDto.setMenuName(menu.getMenuName());
            menuDto.setMenuPrice(menu.getMenuPrice());
            menuList.add(menuDto);
        }
    }
}
