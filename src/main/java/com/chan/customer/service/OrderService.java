package com.chan.customer.service;

import com.chan.customer.client.SellerClient;
import com.chan.customer.common.Message;
import com.chan.customer.common.StatusEnum;
import com.chan.customer.domain.*;
import com.chan.customer.dto.SellerOrderRequestDto;
import com.chan.customer.exception.OrderFindFailedException;
import com.chan.customer.exception.OrderRequestFailedException;
import com.chan.customer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final SellerClient sellerClient;

    @Transactional
    public Order requestOrder(String accountId, List<Menu> menuList){

        //고객 정보 조회
        Customer customer = customerService.findAccountId(accountId);

        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(customer.getAddress());

        //주문 메뉴 생성
        List<OrderMenu> orderMenuList = new ArrayList<>();
        for(var menu : menuList){
            OrderMenu orderMenu = new OrderMenu();
            orderMenu.setMenu(menu);
            orderMenuList.add(orderMenu);
        }
        Menu menu = menuList.get(0);

        Order order = saveOrder(customer, delivery, orderMenuList);

        //Seller service 주문 요청
        Message sellerOrderResult = sellerClient.requestOrder(SellerOrderRequestDto.builder()
                .customerId(customer.getAccountId())
                .customerOrderId(order.getId())
                .customerName(customer.getName())
                .customerAddress(delivery.getAddress())
                .customerTelephone(customer.getTelephone())
                .menuId(menu.getMenuNo())
                .menuPlan(menu.getMenuPlan())
                .menuCount(menu.getMenuCount())
                .build());

        if(!sellerOrderResult.getStatus().equals(StatusEnum.OK)){
            throw new OrderRequestFailedException(sellerOrderResult.getMessage());
        }

        return order;

    }

    public Order findOrder(Long orderId){

        //주문 정보 조회
        Order order = orderRepository.findById(orderId).orElseThrow(OrderFindFailedException::new);

        return order;
    }

    public List<Order> findOrder(String accountId, LocalDate start, LocalDate end){

        //고객 정보 조회
        Customer customer = customerService.findAccountId(accountId);

        LocalDateTime startTime = start.atStartOfDay();
        LocalDateTime endTime = end.atTime(LocalTime.MAX);

        //주문 정보 조회
        List<Order> order = orderRepository.findAllByCustomerAndOrderDateTimeBetween(customer, startTime, endTime);

        return order;
    }

    @Transactional
    public Order updateOrder(Long orderId, OrderStatus newOrderStatus){

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderFindFailedException("주문 아이다가 존재하지 않습니다."));

        order.setStatus(newOrderStatus);

        return order;
    }

    private Order saveOrder(Customer customer, Delivery delivery, List<OrderMenu> orderMenuList){

        //주문 생성
        Order order = Order.request(customer, delivery, orderMenuList);

        //주문 저장
        orderRepository.save(order);

        return order;
    }
}
