package com.chan.customer.controller;

import com.chan.customer.common.Message;
import com.chan.customer.common.StatusEnum;
import com.chan.customer.domain.Customer;
import com.chan.customer.domain.Menu;
import com.chan.customer.domain.Order;
import com.chan.customer.dto.*;
import com.chan.customer.exception.OrderRequestValidationFailedException;
import com.chan.customer.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/order")
public class OrderController {

    private final OrderService orderService;

    private final ObjectMapper objectMapper;

    @GetMapping("/{orderId}")
    public ResponseEntity<Message> order(@PathVariable Long orderId){

        Message message = new Message();

        Order order = orderService.findOrder(orderId);

        message.setStatus(StatusEnum.OK);
        message.setMessage("주문 조회 성공");
        message.setData(new OrderResponseDto(order));

        return ResponseEntity.ok().body(message);
    }

    @GetMapping
    public ResponseEntity<Message> orders(@RequestBody OrderFindRequestDto orderFindCondition) {
        Message message = new Message();

        List<Order> order = orderService.findOrder(orderFindCondition.getAccountId(), orderFindCondition.getStartDate(), orderFindCondition.getEndDate());

        message.setStatus(StatusEnum.OK);
        message.setMessage("주문 조회 성공");
        message.setData(order.stream().map(OrderResponseDto::new));

        return ResponseEntity.ok().body(message);
    }

    @PostMapping
    public ResponseEntity<Message> requestOrder(@Valid @RequestBody OrderRequestDto orderDto, Errors errors) throws JsonProcessingException {

        Message message = new Message();

        if (errors.hasErrors()) {
            throw new OrderRequestValidationFailedException(objectMapper.writeValueAsString(errors));
        }

        Order order = orderService.requestOrder(orderDto.getAccountId(), orderDto.getMenu());

        message.setStatus(StatusEnum.OK);
        message.setMessage("주문 성공");
        message.setData(new OrderResponseDto(order));

        return ResponseEntity.ok().body(message);
    }

    @PutMapping
    public ResponseEntity<Message> updateOrder(@RequestBody OrderUpdateDto orderUpdateDto){

        Message message = new Message();

        Order order = orderService.updateOrder(orderUpdateDto.getOrderId(), orderUpdateDto.getOrderStatus());

        message.setStatus(StatusEnum.OK);
        message.setMessage("주문 상태 변경 완료");
        message.setData(new OrderResponseDto(order));

        return ResponseEntity.ok().body(message);
    }

}
