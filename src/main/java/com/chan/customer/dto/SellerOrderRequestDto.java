package com.chan.customer.dto;

import com.chan.customer.domain.Address;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class SellerOrderRequestDto {

    @NotEmpty
    private String customerId;

    @NotEmpty
    private Long customerOrderId;

    @NotEmpty
    private String customerName;

    @NotEmpty
    private Address customerAddress;

    @NotEmpty
    private String customerTelephone;

    @NotEmpty
    private Long menuId;

    @NotEmpty
    private String menuName;

    @NotEmpty
    @Size(min=7, max=7)
    private String menuPlan;

    @Min(0)
    private int menuPrice;

    @Min(1)
    private int menuCount;
}
