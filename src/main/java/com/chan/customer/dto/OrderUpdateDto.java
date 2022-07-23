package com.chan.customer.dto;

import com.chan.customer.domain.OrderStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderUpdateDto {

    @NotEmpty
    private Long orderId;

    @NotEmpty
    private OrderStatus orderStatus;
}
