package com.chan.customer.controller;

import com.chan.customer.common.Message;
import com.chan.customer.common.StatusEnum;
import com.chan.customer.domain.Customer;
import com.chan.customer.dto.CustomerDto;
import com.chan.customer.dto.CustomerResponseDto;
import com.chan.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/member")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Message> findCustomer(@PathVariable String accountId){

        Message message = new Message();

        Customer customer = customerService.findAccountId(accountId);

        message.setStatus(StatusEnum.OK);
        message.setMessage("조회 성공");
        message.setData(new CustomerResponseDto(customer));

        return ResponseEntity.ok().body(message);
    }

    @PostMapping
    public ResponseEntity<Message> signUp(@RequestBody CustomerDto customerDto){

        Message message = new Message();

        Customer customer = customerService.signUp(customerDto.toEntity());

        message.setStatus(StatusEnum.OK);
        message.setMessage("가입 성공");
        message.setData(new CustomerResponseDto(customer));

        return ResponseEntity.ok().body(message);
    }

    @PutMapping
    public ResponseEntity<Message> updateProfile(@RequestBody CustomerDto customerDto) {

        Message message = new Message();

        Customer customer = customerService.update(customerDto.toEntity());

        message.setStatus(StatusEnum.OK);
        message.setMessage("업데이트 성공");
        message.setData(new CustomerResponseDto(customer));

        return ResponseEntity.ok().body(message);
    }

}
