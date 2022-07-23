package com.chan.customer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderFindRequestDto {

    @NotEmpty
    private String accountId;

    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate startDate;

    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
