package com.chan.customer.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequestDto {

    @NotEmpty
    private String accountId;

    @Valid
    private List<MenuDto> menuDtoList = new ArrayList<>();

}